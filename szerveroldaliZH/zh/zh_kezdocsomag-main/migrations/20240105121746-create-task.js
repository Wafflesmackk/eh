'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable("Tasks", {
        id: {
            allowNull: false,
            autoIncrement: true,
            primaryKey: true,
            type: Sequelize.INTEGER,
        },
        name: {
            allowNull: false,
            type: Sequelize.STRING,
        },
        description: {
            allowNull: false,
            type: Sequelize.STRING,
        },
        deadline: {
            allowNull: false,
            type: Sequelize.DATE,
        },
        weight: {
            allowNull: false,
            type: Sequelize.FLOAT,
        },
        ProjectID: {
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
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('Tasks');
  }
};