const auth = require("./auth");
const db = require("../models");
const { Sequelize, sequelize } = db;
const { ValidationError, DatabaseError, Op } = Sequelize;
// TODO: Importáld a modelleket
const { Employee, Progress,Project,Task } = db;

module.exports = {
    Query: {
        /*
        // Elemi Hello World! példa:
        helloWorld: () => "Hello World!",

        // Példa paraméterezésre:
        helloName: (_, { name }) => `Hello ${name}!`,*/

        // TODO: Dolgozd ki a további resolver-eket a schema-val összhangban
        project: async (_, { id }) => {
            try {
                const project = await Project.findByPk(id);

                if (!project) {
                    return null; // If the project doesn't exist, return null
                }

                return project;
            } catch (error) {
                console.error("Error fetching project:", error);
                throw new DatabaseError("Error fetching project");
            }
        },
        codeGenerator: async() =>{
            const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            let code = "";

            for (let i = 0; i < 6; i++) {
                const randomIndex = Math.floor(Math.random() * characters.length);
                code += characters[randomIndex];
            }

            return code;
        },
        statistics: async () => {
      // Itt hozzáférhetsz az adatokhoz és elvégezheted a számolásokat
        const projects = await Project.findAll();
        const tasks = await Task.findAll();
        const employees = await Employee.findAll();


        const projectsPairTasks = projects.filter((project) => {
        const projectTasks = tasks.filter((task) => task.projectId === project.id);
        return projectTasks.length % 2 === 0;
        }).length;

        const expiredTasks = tasks.filter((task) => task.deadline < new Date()).length;

        const employeesWithoutTasks = employees.filter((employee) => {
        const employeeTasks = tasks.filter((task) => task.assignedTo === employee.id);
        return employeeTasks.length === 0;
        }).length;

        return {
            projectsPairTasks,
            expiredTasks,
            employeesWithoutTasks,
            };
        },
    },
    Mutation: {
        createEmployee: auth(async (parent, args, context) => {
            try{
                console.log(context.user.email);
                const employee = args.employee;
                const emailRegex = /^employee\d+@example\.com$/;
                const employeeTry = await Employee.findOne({
                    where: { email: employee.email },
                });
                    if (context.user.email != "projectmanager@example.com") {
                        throw new Error("You are not authorized to create employees");
                    }
                    else if(typeof employee.name != 'string' || employee.name.length < 3){
                        throw new Error("Invalid name");
                    }
                    else if (typeof employee.email != "string" || !emailRegex.test(employee.email)) {
                        throw new Error("Invalid email");
                    }
                    else if(employeeTry){
                        throw new Error("This email is already in use");
                    }
                    else{
                    const createdEmployee = await Employee.create({
                        name: employee.name,
                        email: employee.email,
                    });

                    return createdEmployee;
                }
            }
            catch(error) {
                console.error(error);
                throw error;
            }
        }),

        deleteEmployees: auth(async (parent, args, context) => {
            try{
                user = context.user
                if (user.email !== "projectmanager@example.com") {
                    throw new Error("You are not authorized to delete employees");
                }
                const deletionResult = {
                    invalid: [],
                    deleted: [],
                };
                // Töröljük az alkalmazottakat
                for (const email of args.emails) {
                    const employee = await Employee.findOne({
                        where: { email },
                    });

                    if (employee) {
                        // Az alkalmazott létezik, töröljük
                        await employee.destroy();
                        deletionResult.deleted.push(email);
                    } else {
                        // Az alkalmazott nem létezik
                        deletionResult.invalid.push(email);
                    }
                }

                return deletionResult;
            } catch (error) {
                console.error("Error deleting employees:", error);
                throw error;
            }
        }),
    },
    Project: {
        tasks: async (project) => {
            try {
                const tasks = await Task.findAll({
                    where: { projectId: project.id },
                    order: [["weight", "DESC"]], // Sort tasks by weight in descending order
                });

                return tasks;
            } catch (error) {
                console.error("Error fetching tasks:", error);
                throw new DatabaseError("Error fetching tasks");
            }
        },
        progress: async (project) => {
            try {
                const tasks = await Task.findAll({
                    where: { projectId: project.id },
                });

                if (tasks.length === 0) {
                    return 0;
                }

                const totalWeight = tasks.reduce((acc, task) => acc + task.weight, 0);

                let overallProgress = 0;

                for (const task of tasks) {
                    const latestProgress = await Progress.findAll({
                        where: { taskId: task.id },
                        order: [["createdAt", "DESC"]],
                    });
                    const taskProgress  = 0
                    if(latestProgress != null){
                        if(latestProgress.progress != null){
                            taskProgress =latestProgress.progress;
                        }
                        console.log(taskProgress);
                    }

                    if (taskProgress === null || taskProgress === undefined) {
                        console.error(`Task.progress is null or undefined for task with ID: ${task.id}`);
                    } else {
                        overallProgress += taskProgress * task.weight;
                    }
                }

                overallProgress /= totalWeight;
                return overallProgress || 0;
            } catch (error) {
                console.error("Error calculating project progress:", error);
                throw new DatabaseError("Error calculating project progress");
            }
        },
        employees: async (project) => {
            try {
                const tasks = await Task.findAll({
                    where: { projectId: project.id },
                    include: [{ model: Employee, through: "EmployeeTask" }],
                });

                // Alkalmazottak neveinek ABC sorrendben rendezése
                const uniqueEmployees = Array.from(
                    new Set(tasks.flatMap((task) => task.Employees.map((employee) => employee.get({ plain: true }))))
                );
                const data = uniqueEmployees.sort((a, b) => a.name.localeCompare(b.name));

                const uniqueEmployeees = {};

               // Minden embert egyedi azonosítóval tárolunk
                data.forEach((employee) => {
                    uniqueEmployeees[employee.id] = employee;
                });

               // Az egyedi emberek tömbjének elkészítése
                const data2 = Object.values(uniqueEmployeees);
                const sortedEmployees = data2.sort((a, b) => a.name.localeCompare(b.name));


                return sortedEmployees;
            } catch (error) {
                console.error("Error fetching project employees:", error);
                throw new DatabaseError("Error fetching project employees");
            }
        },
    },
    Task: {
        progress: async (task) => {
            try {
                const latestProgress = await Progress.findOne({
                    where: { taskId: task.id },
                    order: [["createdAt", "DESC"]],
                });

                return latestProgress ? latestProgress.progress : null;
            } catch (error) {
                console.error("Error fetching progress:", error);
                throw new DatabaseError("Error fetching progress");
            }
        },
    },
};
