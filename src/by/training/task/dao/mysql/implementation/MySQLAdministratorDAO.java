package by.training.task.dao.mysql.implementation;

import by.training.task.dao.interfaces.AdministratorDAO;
import by.training.task.dao.mysql.config.ConfigurationManager;
import by.training.task.dao.mysql.factory.MySQLDAOFactory;
import by.training.task.entities.Administrator;
import by.training.task.utils.LoggerManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table administrator in MySQL database
 * @author Anton Puhachou
 * @see by.training.task.dao.interfaces.AdministratorDAO
 * */
public class MySQLAdministratorDAO implements AdministratorDAO {

    /**
     * method of obtaining a specific {@link Administrator} entity by id
     * @param id id of entity
     * @return {@link Administrator} entity
     * @throws SQLException error close result set or connection
     */
    @Override
    public Administrator get(int id) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        Administrator entity = null;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryAdminGet());
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    entity = new Administrator(resultSet.getInt(1),
                                            resultSet.getString(2),
                                            resultSet.getString(3));
                }
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                MySQLDAOFactory.closeConnection(connection);
            }
        }
        return entity;
    }

    /**
     * method of adding an {@link Administrator} entity to the database
     * @param entity to add in database
     * @return id of added entity
     * @throws SQLException error close connection
     */
    @Override
    public int create(Administrator entity) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int newId = -1;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryAdminCreate());
                preparedStatement.setString(1, entity.getLogin());
                preparedStatement.setString(2, entity.getPassword());
                newId = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            } finally {
                MySQLDAOFactory.closeConnection(connection);
            }
        }
        return newId;
    }

    /**
     * method of updating an {@link Administrator} entity in the database
     * @param entity to update in database
     * @return id of updated entity
     * @throws SQLException error close connection
     */
    @Override
    public int update(Administrator entity) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int updatedId = -1;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryAdminUpdate());
                preparedStatement.setString(1, entity.getLogin());
                preparedStatement.setString(2, entity.getPassword());
                preparedStatement.setInt(3, entity.getId());
                updatedId = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            } finally {
                MySQLDAOFactory.closeConnection(connection);
            }
        }
        return updatedId;
    }

    /**
     * method of deleting an {@link Administrator} entity in the database
     * @param id to delete in database
     * @return id of deleted entity
     * @throws SQLException error close connection
     */
    @Override
    public int delete(int id) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int deletedId = -1;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryAdminDelete());
                preparedStatement.setInt(1, id);
                deletedId = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            } finally {
                MySQLDAOFactory.closeConnection(connection);
            }
        }
        return deletedId;
    }

    /**
     * method of getting all of {@link Administrator} entities from the database
     * @return list of entities
     * @throws SQLException error close result set or connection
     */
    @Override
    public List<Administrator> getAll() throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        List<Administrator> entities = new ArrayList<>();
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryAdminGetAll());
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    entities.add(new Administrator(resultSet.getInt(1),
                                                resultSet.getString(2),
                                                resultSet.getString(3)));
                }
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                MySQLDAOFactory.closeConnection(connection);
            }
        }
        return entities;
    }
}
