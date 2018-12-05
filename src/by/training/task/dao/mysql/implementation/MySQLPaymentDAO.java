package by.training.task.dao.mysql.implementation;

import by.training.task.dao.interfaces.PaymentDAO;
import by.training.task.dao.mysql.config.ConfigurationManager;
import by.training.task.dao.mysql.factory.MySQLDAOFactory;
import by.training.task.entities.Payment;
import by.training.task.utils.LoggerManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table payment in MySQL database
 * @author Anton Puhachou
 * @see by.training.task.dao.interfaces.PaymentDAO
 * */
public class MySQLPaymentDAO implements PaymentDAO {

    /**
     * method of obtaining a specific {@link Payment} entity by id
     * @param id id of entity
     * @return {@link Payment} entity
     * @throws SQLException error close result set or connection
     */
    @Override
    public Payment get(int id) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        Payment entity = null;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPaymentGet());
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    entity = new Payment(resultSet.getInt(1), resultSet.getInt(2),
                            resultSet.getBoolean(3));
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
     * method of adding an {@link Payment} entity to the database
     * @param entity to add in database
     * @return id of added entity
     */
    @Override
    public int create(Payment entity) {
        Connection connection = MySQLDAOFactory.createConnection();
        int newId = -1;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPaymentCreate());
                preparedStatement.setInt(1, entity.getCost());
                preparedStatement.setBoolean(2, entity.isPayed());
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
     * method of updating an {@link Payment} entity in the database
     * @param entity to update in database
     * @return id of updated entity
     */
    @Override
    public int update(Payment entity) {
        Connection connection = MySQLDAOFactory.createConnection();
        int updatedId = -1;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPaymentUpdate());
                preparedStatement.setInt(1, entity.getCost());
                preparedStatement.setBoolean(2, entity.isPayed());
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
     * method of deleting an {@link Payment} entity in the database
     * @param id to delete in database
     * @return id of deleted entity
     */
    @Override
    public int delete(int id) {
        Connection connection = MySQLDAOFactory.createConnection();
        int deletedId = -1;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPaymentDelete());
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
     * method of getting all of {@link Payment} entities from the database
     * @return list of entities
     * @throws SQLException error close result set or connection
     */
    @Override
    public List<Payment> getAll() throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        List<Payment> entities = new ArrayList<>();
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPaymentGetAll());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    entities.add(new Payment(resultSet.getInt(1),
                                            resultSet.getInt(2),
                                            resultSet.getBoolean(3)));
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
