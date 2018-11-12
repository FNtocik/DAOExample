package by.training.taskdao.dao.mysql.implementation;

import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.dao.mysql.config.ConfigurationManager;
import by.training.taskdao.dao.mysql.factory.MySQLDAOFactory;
import by.training.taskdao.entities.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table publication in MySQL database
 * @author Anton Puhachou
 * */
public class MySQLPublicationDAO implements PublicationDAO {

    /**
     * method of obtaining a specific {@link Publication} entity by id
     * @param id id of entity
     * @return {@link Publication} entity
     * @throws SQLException error close result set or connection
     */
    @Override
    public Publication get(int id) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        Publication entity = null;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationGet());
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    entity = new Publication(resultSet.getInt(1),
                                            resultSet.getString(3),
                                            resultSet.getString(2),
                                            resultSet.getInt(4),
                                            resultSet.getInt(5));
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
     * method of adding an {@link Publication} entity to the database
     * @param entity to add in database
     * @return id of added entity
     * @throws SQLException error close connection
     */
    @Override
    public int create(Publication entity) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int newId = -1;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationCreate());
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setString(3, entity.getAuthor());
                preparedStatement.setInt(4, entity.getCost());
                preparedStatement.setInt(5, entity.getLanguageId());
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
     * method of updating an {@link Publication} entity in the database
     * @param entity to update in database
     * @return id of updated entity
     * @throws SQLException error close connection
     */
    @Override
    public int update(Publication entity) throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        int updatedId = -1;
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationUpdate());
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getAuthor());
                preparedStatement.setInt(3, entity.getCost());
                preparedStatement.setInt(5, entity.getLanguageId());
                preparedStatement.setInt(4, entity.getId());
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
     * method of deleting an {@link Publication} entity in the database
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
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationDelete());
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
     * method of getting all of {@link Publication} entities from the database
     * @return list of entities
     * @throws SQLException error close result set or connection
     */
    @Override
    public List<Publication> getAll() throws SQLException {
        Connection connection = MySQLDAOFactory.createConnection();
        ResultSet resultSet = null;
        List<Publication> entities = new ArrayList<>();
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationGetAll());
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    entities.add(new Publication(resultSet.getInt(1),
                                                resultSet.getString(3),
                                                resultSet.getString(2),
                                                resultSet.getInt(4),
                                                resultSet.getInt(5)));
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
