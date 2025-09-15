const { StatusCodes } = require("http-status-codes");
const S = require("fluent-json-schema");
const db = require("../models");
const { default: request } = require("graphql-request");
const { forIn } = require("lodash");
const task = require("../models/task");
const { Model, where } = require("sequelize");
const project = require("../models/project");
const { all } = require("axios");
const { Sequelize, sequelize } = db;
const { ValidationError, DatabaseError, Op } = Sequelize;
// TODO: Importáld a modelleket
const { Employee, Progress,Project,Task } = db;

module.exports = function (fastify, opts, next) {
    // http://127.0.0.1:4000/
    fastify.get("/", async (request, reply) => {
        reply.send({ message: "Gyökér végpont" });

        // NOTE: A send alapból 200 OK állapotkódot küld, vagyis az előző sor ugyanaz, mint a következő:
        // reply.status(200).send({ message: "Gyökér végpont" });

        // A 200 helyett használhatsz StatusCodes.OK-ot is (így szemantikusabb):
        // reply.status(StatusCodes.OK).send({ message: "Gyökér végpont" });
    });

    fastify.get("/projects", async (request, reply) =>{
        const tmpreply = [];
        const projects = await Project.findAll({
            include: [{ model: Task }],
        });
        //console.log(projects.map((project) => project.toJSON()));
        for (const project of projects) {
            const taskCount = await project.Tasks.length;
            const pushReply = {
                id: project.id,
                name: project.name,
                description: project.description,
                deadline: project.deadline,
                createdAt: project.createdAt,
                updatedAt: project.updatedAt,
                tasks: taskCount,
            };
            tmpreply.push(pushReply);
        }
        reply.send(tmpreply);
    })

    fastify.get("/tasks/:id/progress",
    {
        schema: {
            params: {
                type: "object",
                required: ["id"],
                properties: {
                    id: { type: "integer" },
                },
            },
        },
    }, async (request, reply) => {
        const { id } = request.params;
        const task = await Task.findByPk(id, {
        include: [
            {
                model: Progress,
                order: [
                    ["createdAt", "DESC"],
                    ["updatedAt", "DESC"],
                ],
            },
        ],
    });
        if (!task) {
            return reply.status(404).send();
        }
        const firstProgress = task.Progresses[0];
        if(!firstProgress){
            const tmpreply = {
                progress: 0
            };
            return reply.send(tmpreply);
        }
        const latestProgress = firstProgress.dataValues.progress;
        console.log(latestProgress)
        const tmpreply = {
            progress: latestProgress
        }
        reply.send(tmpreply);
    });


    fastify.post(
        "/create-project",
        {
            preHandler: async (request, reply) => {
                try {
                    // Ensure the request is authenticated with a valid JWT token
                    await request.jwtVerify();

                    // Get the user's email from the token payload
                    const userEmail = request.user.email;

                    // Check if the user is a project manager (assuming the project manager email is projectmanager@example.com)
                    if (userEmail !== "projectmanager@example.com") {
                        reply.status(403).send({ message: "Forbidden. Only project managers can create projects." });
                    }
                } catch (error) {
                    // Handle authentication errors
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
            schema: {
                body: {
                    type: "object",
                    properties: {
                        name: { type: "string" },
                        description: { type: "string" },
                        code: { type: "string", minLength: 6, maxLength: 6, pattern: "^[A-Z0-9]+$" },
                        deadline: { type: "string", format: "date" },
                    },
                    required: ["name", "description", "code", "deadline"],
                },
            },
        },
        async (request, reply) => {
            const { name, description, code, deadline } = request.body;

            try {
                // Try to create the project in the database
                const createdProject = await Project.create({
                    name,
                    description,
                    code,
                    deadline,
                });
                // Respond with the created project details
                reply.status(201).send({
                    name: createdProject.name,
                    deadline: createdProject.deadline.toISOString().split("T")[0], // Format date as YYYY-MM-DD
                });
            } catch (error) {
                // Handle validation errors (e.g., unique constraint on the code field)
                if (error instanceof Sequelize.UniqueConstraintError && error.fields.includes("code")) {
                    reply.status(409).send({ message: "Conflict. Project with the provided code already exists." });
                } else {
                    // Handle other errors
                    reply.status(500).send({ message: "Internal Server Error" });
                }
            }
        }
    );


    fastify.post(
        "projects/:id/create-task",
        {
            preHandler: async (request, reply) => {
                try {
                    // Ensure the request is authenticated with a valid JWT token
                    await request.jwtVerify();
                    // Get the user's email from the token payload
                    const userEmail = request.user.email;
                    // Check if the user is a project manager (assuming the project manager email is projectmanager@example.com)
                    if (userEmail !== "projectmanager@example.com") {
                        reply.status(403).send({ message: "Forbidden. Only project managers can create projects." });
                    }
                } catch (error) {
                    // Handle authentication errors
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
            schema: {
                params: {
                    type: "object",
                    required: ["id"],
                    properties: {
                        id: { type: "integer" },
                    },
                },
                body: {
                    type: "object",
                    properties: {
                        name: { type: "string" },
                        weight: { type: "number", minimum: 0, maximum: 1 },
                        description: { type: "string" },
                        deadline: { type: "string", format: "date" },
                    },
                    required: ["name", "weight", "description", "deadline"],
                },
            },
        },
        async (request, reply) => {
            const { name, weight, description, deadline } = request.body;
            const { id } = request.params;
            const project = await Project.findByPk(id);

            if (!project) {
                return reply.status(404).send({ message: "Not Found. Project with the provided id does not exist." });
            }

            const createdTask = await Task.create({
                name,
                weight,
                description,
                deadline,
                ProjectId: id,
            });

            reply.status(201).send({
                id: createdTask.id,
                name: createdTask.name,
                weight: createdTask.weight,
                description: createdTask.description,
                deadline: createdTask.deadline.toISOString().split("T")[0], // Format date as YYYY-MM-DD
            });
        }
    );

    fastify.post(
        "/tasks/:id/assign-employees",
        {
            preHandler: async (request, reply) => {
                try {
                    // Ensure the request is authenticated with a valid JWT token
                    await request.jwtVerify();

                    // Get the user's email from the token payload
                    const userEmail = request.user.email;

                    // Check if the user is a project manager (assuming the project manager email is projectmanager@example.com)
                    if (userEmail !== "projectmanager@example.com") {
                        reply
                            .status(403)
                            .send({ message: "Forbidden. Only project managers can assign employees to tasks." });
                    }
                } catch (error) {
                    // Handle authentication errors
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
            schema: {
                params: {
                    type: "object",
                    properties: {
                        id: { type: "integer" },
                    },
                    required: ["id"],
                },
                body: {
                    type: "object",
                    properties: {
                        employees: { type: "array", items: { type: "string", format: "email" } },
                        mode: { type: "string", enum: ["add", "remove"], default: "add" },
                    },
                    required: ["employees"],
                },
            },
        },
        async (request, reply) => {
            const { id } = request.params;
            const { employees, mode } = request.body;
            // Try to find the task by id
            const task = await Task.findByPk(id);

            // If the task does not exist, return a 404 NOT FOUND response
            if (!task) {
                return reply.status(404).send({ message: "Not Found. Task with the provided id does not exist." });
            }

            // Find the existing employees assigned to the task
            const currentEmployees = await task.getEmployees();
            const allEmployees = await Employee.findAll();

            // Separate the employees based on their existence
            const existingEmployees = currentEmployees.map((employee) => employee.email);
            //const invalidEmployees = employees.filter((email) => !allEmployees.includes(email));
            const invalidEmployees = [];
            for (const email of employees){
                const employee = await Employee.findOne({
                    where: { email: email },
                });
                if(!employee){
                    invalidEmployees.push(email)
                }
            }
            const employeesToAdd = employees.filter((email) => !invalidEmployees.includes(email) && mode === "add" && !existingEmployees.includes(email));
            const employeesToRemove = employees.filter(
                (email) => existingEmployees.includes(email) && mode === "remove"
            );

            console.log("Existing Employees:", existingEmployees);
            console.log("Invalid Employees:", invalidEmployees);
            console.log("Employees to Add:", employeesToAdd);
            console.log("Employees to Remove:", employeesToRemove);

            // Perform the adding and removing operations
                if (mode === "add" && employeesToAdd.length > 0) {
            // Retrieve instances of Employee based on their email
            const employeeInstancesToAdd = await Employee.findAll({
                where: { email: employeesToAdd },
            });

            await task.addEmployees(employeeInstancesToAdd); // Use addEmployees for adding multiple employees
        }

        if (mode === "remove" && employeesToRemove.length > 0) {
            // Retrieve instances of Employee based on their email
            const employeeInstancesToRemove = await Employee.findAll({
                where: { email: employeesToRemove },
            });

            await task.removeEmployees(employeeInstancesToRemove); // Use removeEmployees for removing multiple employees
        }

        // Respond with the result
        const result = {
            invalid: invalidEmployees,
            added: mode === "add" ? employeesToAdd.filter(email => !invalidEmployees.includes(email)&& !existingEmployees.includes(email)) : [],
            removed: mode === "remove" ? employeesToRemove.filter(email => existingEmployees.includes(email)) : [],
            current: (await task.getEmployees()).map((employee) => employee.email),
        };

        reply.status(200).send(result);
    });


    fastify.post(
        "/tasks/:id/create-progress",
        {
            preHandler: async (request, reply) => {
                try {
                    // Ensure the request is authenticated with a valid JWT token
                    await request.jwtVerify();

                    // Get the user's email from the token payload
                    const userEmail = request.user.email;

                    // Check if the user is assigned to the specified task
                    const taskId = request.params.id;
                    const task = await Task.findByPk(taskId, {
                        include: Employee,
                    });

                    if (!task || !task.Employees.some((employee) => employee.email === userEmail)) {
                        reply.status(403).send({ message: "Forbidden. You are not assigned to this task." });
                    }
                } catch (error) {
                    // Handle authentication errors
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
            schema: {
                params: {
                    type: "object",
                    properties: {
                        id: { type: "integer" },
                    },
                    required: ["id"],
                },
                body: {
                    type: "object",
                    properties: {
                        progress: { type: "integer", minimum: 0, maximum: 100 },
                        comment: { type: "string" },
                    },
                    required: ["progress"],
                },
            },
        },
        async (request, reply) => {
            const { id } = request.params;
            const { progress, comment } = request.body;

            // Try to find the task by id
            const task = await Task.findByPk(id);

            // If the task does not exist, return a 404 NOT FOUND response
            if (!task) {
                return reply.status(404).send({ message: "Not Found. Task with the provided id does not exist." });
            }

            // Create progress for the task
            const createdProgress = await Progress.create({
                progress,
                comment,
                TaskId: task.id,
            });

            // Get the index of the created progress
            const taskProgresses = await task.getProgresses();
            const index = taskProgresses.findIndex((p) => p.id === createdProgress.id) + 1;

            // Respond with the result
            reply.status(200).send({
                index,
                comment: createdProgress.comment || null,
            });
        }
    );


    // http://127.0.0.1:4000/auth-protected
    fastify.get("/auth-protected", { onRequest: [fastify.auth] }, async (request, reply) => {
        reply.send({ user: request.user });
    });

    next();
};

module.exports.autoPrefix = "/";
