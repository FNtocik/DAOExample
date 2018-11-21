package by.training.taskdao.dao.mysql.implementation;

import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.dao.mysql.config.ConfigurationManager;
import by.training.taskdao.dao.mysql.factory.MySQLDAOFactory;
import by.training.taskdao.entities.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table subscription in MySQL database
 * @author Anton Puhachou
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
                    entity = new Subscription(resultSet.getInt(1),
                                            resultSet.getInt(2),
                                            resultSet.getInt(3),
                                            resultSet.getInt(6),
                                            resultSet.getDate(4),
                                            resultSet.getDate(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(resultSet != null)
                    resultSet.close();
                connection.close();
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
                e.printStackTrace();
            } finally {
                connection.close();
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
                e.printStackTrace();
            } finally {
                connection.close();
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
                e.printStackTrace();
            } finally {
                connection.close();
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
                    entities.add(new Subscription(resultSet.getInt(1),
                                                resultSet.getInt(2),
                                                resultSet.getInt(3),
                                                resultSet.getInt(6),
                                                resultSet.getDate(4),
                                                resultSet.getDate(5)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(resultSet != null)
                    resultSet.close();
                connection.close();
            }
        }
        return entities;
    }
}
