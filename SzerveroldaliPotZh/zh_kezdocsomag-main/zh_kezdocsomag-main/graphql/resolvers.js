const auth = require("./auth");
const db = require("../models");
const { resolveObjMapThunk } = require("graphql");
const { Sequelize, sequelize } = db;
const { ValidationError, DatabaseError, Op } = Sequelize;
// TODO: Importáld a modelleket
const { Group,Student,Teacher } = db;

module.exports = {
    Query: {
        randomGroupName: async (_, { length }) => {
            if (length < 0) {
                return [];
            } else {
                const reply = [];
                const groups = await Group.findAll();
                const indexes = [];
                for (const i in length) {
                    const randomIndex = Math.floor(Math.random() * groups.length);
                    const name = groups[randomIndex];
                    reply.push(randomGroupName);
                }
                return reply;
            }
        },
        classes: async () => {
            const students = await Student.findAll();
            const reply = [];
            for (const student of students) {
                const className = student.semester + ". " + student.classLetter;
                reply.push(className);
            }
            return reply;
        },
        getGroupRelatedStudentsFromClass: async (_, { semester, classLetter }) => {
            const reply = [];
            const students = await Student.findAll();
            for (const student of students) {
                if (student.semester == semester && student.classLetter == classLetter) {
                    reply.push(student.name);
                }
            }
            return reply;
        },
        findGroupBySubstring: async (_, { substrings }) => {
            const reply = [];
            const groups = await Group.findAll();
            for (const group of groups) {
                for (const substring of substrings) {
                    if (group.name.includes(substring)) {
                        reply.push(group.name);
                        break;
                    }
                }
            }
            return reply;
        },
        getGroupsWithStudentCount: async (_, { count }) => {
            const reply = [];
            const groups = await Group.findAll({
                include: [{ model: Student }],
            });
            for (const group of groups) {
                console.log(group.Students);
                if (group.Students.length >= count) {
                    reply.push(groups.name);
                }
            }
            return reply;
        },
        getClassesFromWorkCommunity: async (_, { workCommunity }) => {
            const reply = [];
            const groups = await Group.findAll({
                include: [{ model: Student }],
            });
            for (const group of groups) {
                if (group.workCommunity == workCommunity) {
                    const students = await group.Students;
                    for (const student of students) {
                        const className = student.semester + ". " + student.classLetter;
                        reply.push(className);
                    }
                }
            }
            return reply;
        },
        getGroupsOnlySemesters: async (_, { semesters }) => {
            const reply = [];
            const groups = await Group.findAll({
                include: [{ model: Student }],
            });
            for (const group of groups) {
                if (group.workCommunity == workCommunity) {
                    const students = await group.Students;
                    for (const student of students) {
                        if (semesters.includes(student.semester)) {
                            reply.push(group.name);
                            break;
                        }
                    }
                }
            }
            return reply;
        },
        mostPopularGroup: async () => {
            const max = 0;
            const reply = "";
            const date = "";
            const groups = await Group.findAll({
                include: [{ model: Student }],
            });
            for (const group of groups) {
                if(group.Student.length > max){
                    max = group.Student.length;
                    reply = group.name;
                    date = group.createdAt();
                }
                else if(group.Student.length == max){
                    if(group.createdAt() < date){
                        max = group.Student.length;
                        reply = group.name;
                        date = group.createdAt();
                    }
                }
            }
            return reply;
        }
    },
};
