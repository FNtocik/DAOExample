package by.training.task.dao.mysql.implementation;

import by.training.task.dao.interfaces.LanguageDAO;
import by.training.task.dao.mysql.config.ConfigurationManager;
import by.training.task.dao.mysql.factory.MySQLDAOFactory;
import by.training.task.entities.Language;
import by.training.task.utils.LoggerManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table language in MySQL database
 * @author Anton Puhachou
 * @see by.training.task.dao.interfaces.LanguageDAO
 * */
public class MySQLLanguageDAO implements LanguageDAO {

    /**
     * method of obtaining a specific {@link Language} entity by id
     * @param id id of entity
     * @return {@link Language} entity
     * @throws SQLException error close result set or connection
     */
    @Override
    public Language get(int id) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        Language entity = null;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryLanguageGet());
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    entity = new Language(resultSet.getInt(1),
                                        resultSet.getString(2));
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
     * method of adding an {@link Language} entity to the database
     * @param entity to add in database
     * @return id of added entity
     */
    @Override
    public int create(Language entity) {
        Connection connection = MySQLDAOFactory.createConnection();
        int newId = -1;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryLanguageCreate());
                preparedStatement.setString(1, entity.getSignature());
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
     * method of updating an {@link Language} entity in the database
     * @param entity to update in database
     * @return id of updated entity
     */
    @Override
    public int update(Language entity) {
        Connection connection = MySQLDAOFactory.createConnection();
        int updatedId = -1;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryLanguageUpdate());
                preparedStatement.setString(1, entity.getSignature());
                preparedStatement.setInt(2, entity.getId());
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
     * method of deleting an {@link Language} entity in the database
     * @param id to delete in database
     * @return id of deleted entity
     */
    @Override
    public int delete(int id) {
        Connection connection = MySQLDAOFactory.createConnection();
        int deletedId = -1;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryLanguageDelete());
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
     * method of getting all of {@link Language} entities from the database
     * @return list of entities
     * @throws SQLException error close result set or connection
     */
    @Override
    public List<Language> getAll() throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        List<Language> entities = new ArrayList<>();
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryLanguageGetAll());
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    entities.add(new Language(resultSet.getInt(1),
                                            resultSet.getString(2)));
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
