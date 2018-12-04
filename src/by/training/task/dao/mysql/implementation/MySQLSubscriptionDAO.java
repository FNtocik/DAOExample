package by.training.task.dao.mysql.implementation;

import by.training.task.dao.interfaces.SubscriptionDAO;
import by.training.task.dao.mysql.config.ConfigurationManager;
import by.training.task.dao.mysql.factory.MySQLDAOFactory;
import by.training.task.entities.Payment;
import by.training.task.entities.Publication;
import by.training.task.entities.Reader;
import by.training.task.entities.Subscription;
import by.training.task.utils.LoggerManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table subscription in MySQL database
 * @author Anton Puhachou
 * @see by.training.task.dao.interfaces.SubscriptionDAO
 * */
public class MySQLSubscriptionDAO implements SubscriptionDAO {

    /**
     * method of obtaining a specific {@link Subscription} entity by id
     * @param id id of entity
     * @return {@link Subscription} entity
     * @throws SQLException error close result set or connection
     */
    @Override
    public Subscription get(int id) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        Subscription entity = null;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQuerySubscriptionGet());
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    Reader readerEntity = new Reader(resultSet.getInt("reader.id"),
                            resultSet.getString("login"), resultSet.getString("password"),
                            resultSet.getInt("reader.language_id"));
                    Publication publicationEntity = new Publication(resultSet.getInt("publication.id"),
                            resultSet.getString("author"), resultSet.getString("name"),
                            resultSet.getInt("publication.cost"), resultSet.getInt("publication.language_id"));
                    Payment paymentEntity = new Payment(resultSet.getInt("payment.id"), resultSet.getInt("cost"),
                            resultSet.getBoolean("is_payed"));
                    entity = new Subscription(resultSet.getInt("subscription.id"), readerEntity,
                            publicationEntity, paymentEntity, resultSet.getDate("start_subs"),
                            resultSet.getDate("end_subs"));
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
     * method of adding an {@link Subscription} entity to the database
     * @param entity to add in database
     * @return id of added entity
     * @throws SQLException error close connection
     */
    @Override
    public int create(Subscription entity) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int newId = -1;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQuerySubscriptionCreate());
                preparedStatement.setInt(1, entity.getReaderId());
                preparedStatement.setInt(2, entity.getPublicationId());
                preparedStatement.setDate(3, entity.getStartSubscription());
                preparedStatement.setDate(4, entity.getEndSubscription());
                preparedStatement.setInt(5, entity.getPaymentId());
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
     * method of updating an {@link Subscription} entity in the database
     * @param entity to update in database
     * @return id of updated entity
     * @throws SQLException error close connection
     */
    @Override
    public int update(Subscription entity) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int updatedId = -1;
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQuerySubscriptionUpdate());
                preparedStatement.setInt(1, entity.getReaderId());
                preparedStatement.setInt(2, entity.getPublicationId());
                preparedStatement.setDate(3, entity.getStartSubscription());
                preparedStatement.setDate(4, entity.getEndSubscription());
                preparedStatement.setInt(5, entity.getPaymentId());
                preparedStatement.setInt(6, entity.getId());
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
     * method of deleting an {@link Subscription} entity in the database
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
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQuerySubscriptionDelete());
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
     * method of getting all of {@link Subscription} entities from the database
     * @return list of entities
     * @throws SQLException error close result set or connection
     */
    @Override
    public List<Subscription> getAll() throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        List<Subscription> entities = new ArrayList<>();
        if(connection != null) {
            try {
                PreparedStatement
                        preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQuerySubscriptionGetAll());
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Reader readerEntity = new Reader(resultSet.getInt("reader.id"),
                            resultSet.getString("login"), resultSet.getString("password"),
                            resultSet.getInt("reader.language_id"));
                    Publication publicationEntity = new Publication(resultSet.getInt("publication.id"),
                            resultSet.getString("author"), resultSet.getString("name"),
                            resultSet.getInt("publication.cost"), resultSet.getInt("publication.language_id"));
                    Payment paymentEntity = new Payment(resultSet.getInt("payment.id"), resultSet.getInt("cost"),
                            resultSet.getBoolean("is_payed"));
                    entities.add(new Subscription(resultSet.getInt("subscription.id"), readerEntity,
                            publicationEntity, paymentEntity, resultSet.getDate("start_subs"),
                            resultSet.getDate("end_subs")));
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
