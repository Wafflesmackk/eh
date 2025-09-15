"use strict";

/** @type {import('sequelize-cli').Migration} */
module.exports = {
    async up(queryInterface, Sequelize) {
        await queryInterface.createTable("EmployeeTask", {
            id: {
                allowNull: false,
                autoIncrement: true,
                primaryKey: true,
                type: Sequelize.INTEGER,
            },
            TaskId: {
                allowNull: false,
                type: Sequelize.INTEGER,
            },
            EmployeeId: {
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
        await queryInterface.addConstraint("EmployeeTask", {
            fields: ["EmployeeId", "TaskId"],
            type: "unique",
        });
    },

    async down(queryInterface, Sequelize) {
        await queryInterface.dropTable("EmployeeTask");
    },
};
