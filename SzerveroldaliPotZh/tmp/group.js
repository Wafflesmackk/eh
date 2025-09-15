'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Group extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      this.belongsToMany(models.Teacher, { through: "TeacherGroup" });
      this.belongsToMany(models.Student, {
        through: models.GroupStudent,
    });
    }
  }
  Group.init({
    name: DataTypes.STRING,
    workingCommunity: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'Group',
  });
  return Group;
};
