'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable("Progresses", {
        id: {
            allowNull: false,
            autoIncrement: true,
            primaryKey: true,
            type: Sequelize.INTEGER,
        },
        progress: {
            allowNull: false,
            type: Sequelize.INTEGER,
        },
        comment: {
            type: Sequelize.STRING,
        },
        createdAt: {
            allowNull: false,
            type: Sequelize.DATE,
        },
        EmployeeID: {
            allowNull: false,
            type: Sequelize.INTEGER,
        },
        TaskID: {
            allowNull: false,
            type: Sequelize.INTEGER,
        },
        updatedAt: {
            allowNull: false,
            type: Sequelize.DATE,
        },
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('Progresses');
  }
};