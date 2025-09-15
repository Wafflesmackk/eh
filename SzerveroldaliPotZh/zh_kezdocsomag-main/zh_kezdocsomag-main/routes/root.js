const { StatusCodes } = require("http-status-codes");
const S = require("fluent-json-schema");
const db = require("../models");
const { ProvidedRequiredArgumentsOnDirectivesRule } = require("graphql/validation/rules/ProvidedRequiredArgumentsRule");
const { compressToEncodedURIComponent } = require("lz-string");
const group = require("../models/group");
const { Sequelize, sequelize } = db;
const { ValidationError, DatabaseError, Op } = Sequelize;
// TODO: Importáld a modelleket
const { Group, Student, Teacher, GroupStudent} = db;

module.exports = function (fastify, opts, next) {
    // http://127.0.0.1:4000/
    fastify.get("/", async (request, reply) => {
        reply.send({ message: "Gyökér végpont" });

        // NOTE: A send alapból 200 OK állapotkódot küld, vagyis az előző sor ugyanaz, mint a következő:
        // reply.status(200).send({ message: "Gyökér végpont" });

        // A 200 helyett használhatsz StatusCodes.OK-ot is (így szemantikusabb):
        // reply.status(StatusCodes.OK).send({ message: "Gyökér végpont" });
    });
    fastify.post(
        "/integers",
        {
            schema: {
                body: {
                    type: "array",
                },
            },
        },
        async (request, reply) => {
            const { body } = request;
            if (!Array.isArray(body)) {
                return reply.status(400).send({ message: "Bad Request. Request body must be an array." });
            }

            const processedIntegers = [];
            const notIntegers = [];
            let randomInteger = null;
            for (const item of body) {
                if (Number.isInteger(item)) {
                    processedIntegers.push(item % 2 === 1 ? item * 2 : item);
                } else {
                    notIntegers.push(item);
                }
            }


            if (processedIntegers.length > 0) {
                const randomIndex = Math.floor(Math.random() * processedIntegers.length);
                randomInteger = processedIntegers[randomIndex];
            }

            reply.status(200).send({
                processedIntegers,
                notIntegers,
                randomInteger,
            });
        }
    );

    fastify.get('/groups',
    //Postman és normál lekérdezéssel működik a tester valamiért 500 errort ad
        async (request, reply) => {
            const groups = await Group.findAll({
                include: [{model: Teacher},
                    {model:Student}],
            });
            const tmpReply = [];
            for(const group of groups){
                const students = await group.Students;
                const teachers = await group.Teachers;
                const pushStudent = [];
                const pushTeacher = [];
                for(const student of students){
                    if(await student.GroupStudent.status == 'ACCEPTED'){
                        pushStudent.push({name: student.name});
                }
                }
                for(const teacher of teachers){
                    pushTeacher.push({name: teacher.name});
                }
                const pushReply = {
                    id: group.id,
                    name: group.name,
                    workingCommunity: group.workingCommunity,
                    createdAt: group.createdAt,
                    updatedAt: group.updatedAt,
                    Teachers: pushTeacher,
                    Students: pushStudent,
                };
                tmpReply.push(pushReply);

            }
            return reply.status(200).send(tmpReply);
        });
    fastify.post(
        "/teacher/create-student",
        {
            preHandler: async (request, reply) => {
                try {
                    await request.jwtVerify();
                    const userEmail = request.user.email;
                    const teacher = await Teacher.findOne({
                        where: { email: userEmail },
                    });
                    if (!teacher) {
                        reply.status(403).send({ message: "Forbidden" });
                    }
                } catch (error) {
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
            schema: {
                body: {
                    type: "object",
                    properties: {
                        name: { type: "string" },
                        email: { type: "string", format: "email" },
                        classData: { type: "string" },
                    },
                    required: ["name", "email", "classData"],
                },
            },
        },
        async (request, reply) => {
            const { name, email, classData } = request.body;
            const semester = classData.split(".")[0];
            const classLetter = classData.split(".")[1];
            if(classLetter == null){
                const semester = classData.split("/")[0];
                const classLetter = classData.split("/")[1];
            }

            try {
                const createadStudent = await Student.create({
                    name,
                    email,
                    semester,
                    classLetter,
                });
                if (createadStudent) {
                    return reply.status(201).send(createadStudent);
                }
            } catch (error) {
                if (error instanceof Sequelize.UniqueConstraintError && error.fields.includes("code")) {
                    reply.status(409).send({ message: "Conflict. Project with the provided code already exists." });
                } else {
                    reply.status(500).send({ message: "Internal Server Error" });
                }
            }
        }
    );

    fastify.get(
        "/student/my-groups",
        {
            preHandler: async (request, reply) => {
                try {
                    await request.jwtVerify();
                    const userEmail = request.user.email;
                    const student = await Student.findOne({
                        where: { email: userEmail },
                    });
                    if (!student) {
                        reply.status(403).send({ message: "Forbidden" });
                    }
                } catch (error) {
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
        },
        async (request, reply) => {
            const tmpReply = [];
            reply.send(tmpReply);
        }
    );

    fastify.post("/student/groups/:id/request-join",{
        preHandler: async (request, reply) => {
                try {
                    await request.jwtVerify();
                    const userEmail = request.user.email;
                    const student = await Student.findOne({
                        where: { email: userEmail },
                    });
                    if (!student) {
                        reply.status(403).send({ message: "Forbidden" });
                    }
                } catch (error) {
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
    },async (request, reply) => {
        const tmpReply = [];
            reply.send(tmpReply);
    });

    fastify.get(
        "/teacher/join-requests",
        {
            preHandler: async (request, reply) => {
                try {
                    await request.jwtVerify();
                    const userEmail = request.user.email;
                    const teacher = await Teacher.findOne({
                        where: { email: userEmail },
                    });
                    if (!teacher) {
                        reply.status(403).send({ message: "Forbidden" });
                    }
                } catch (error) {
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },
        },
        async (request, reply) => {
        const tmpReply = [];
        const teacher = await Teacher.findByOne({
            where: { email: request.user.email },
            }
        )
        const groups = await Group.findAll({
            include: [{ model: Teacher }, { model: Student }],
        });
        for(const group of groups){
            if(group.Teachers.include(teacher)){
                tmpReply.push(group);
            }
        }
        reply.send(tmpReply);

        }
    );

    fastify.post(
        "/groups/:id/approve-requests",
        {
            preHandler: async (request, reply) => {
                try {
                    await request.jwtVerify();
                    const userEmail = request.user.email;
                    const teacher = await Teacher.findOne({
                        where: { email: userEmail },
                    });
                    if (!teacher) {
                        reply.status(403).send({ message: "Forbidden" });
                    }
                } catch (error) {
                    reply.status(401).send({ message: "Unauthorized. Please provide a valid JWT token." });
                }
            },

        },
        async (request, reply) => {
            const tmpReply = [];
            reply.send(tmpReply);
        }
    );


    // http://127.0.0.1:4000/auth-protected
    fastify.get("/auth-protected", { onRequest: [fastify.auth] }, async (request, reply) => {
        reply.send({ user: request.user });
    });

    next();
};

module.exports.autoPrefix = "/";
