"use strict";

/** @type {import('sequelize-cli').Migration} */
module.exports = {
    async up(queryInterface, Sequelize) {
        await queryInterface.createTable("TeacherGroup", {
            id: {
                allowNull: false,
                autoIncrement: true,
                primaryKey: true,
                type: Sequelize.INTEGER,
            },
            GroupId: {
                allowNull: false,
                type: Sequelize.INTEGER,
            },
            TeacherId: {
                allowNull: false,
                type: Sequelize.INTEGER,
            },
            createdAt: {
                allowNull: false,
                type: Sequelize.DATE,
            },
            updatedAt: {
                allowNull: false,
                type: Sequelize.DATE,
            },
        });
        await queryInterface.addConstraint("TeacherGroup", {
            fields: ["TeacherId", "GroupId"],
            type: "unique",
        });
    },

    async down(queryInterface, Sequelize) {
        await queryInterface.dropTable("EmployeeTask");
    },
};
