package by.training.task.dao.mysql.implementation;

import by.training.task.dao.interfaces.PublicationDAO;
import by.training.task.dao.mysql.config.ConfigurationManager;
import by.training.task.dao.mysql.factory.MySQLDAOFactory;
import by.training.task.entities.Language;
import by.training.task.entities.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access implementation class to the table publication in MySQL database
 * @author Anton Puhachou
 * @see by.training.task.dao.interfaces.PublicationDAO
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
                PreparedStatement preparedStatement =
                        connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationGet());
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    Language languageEntity = new Language(resultSet.getInt(6), resultSet.getString(7));
                    entity = new Publication(resultSet.getInt(1),
                                            resultSet.getString(3),
                                            resultSet.getString(2),
                                            resultSet.getInt(4),
                            languageEntity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(resultSet != null)
                    resultSet.close();
                MySQLDAOFactory.closeConnection(connection);
                ;
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
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getAuthor());
                preparedStatement.setInt(3, entity.getCost());
                preparedStatement.setInt(4, entity.getLanguageId());
                newId = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySQLDAOFactory.closeConnection(connection);
                ;
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
                preparedStatement.setInt(4, entity.getId());
                preparedStatement.setInt(5, entity.getLanguageId());
                updatedId = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySQLDAOFactory.closeConnection(connection);
                ;
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
                MySQLDAOFactory.closeConnection(connection);
                ;
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
                PreparedStatement preparedStatement =
                        connection.prepareStatement(ConfigurationManager.getInstance().getMySQLQueryPublicationGetAll());
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Language languageEntity = new Language(resultSet.getInt(6), resultSet.getString(7));
                    entities.add(new Publication(resultSet.getInt(1),
                                                resultSet.getString(3),
                                                resultSet.getString(2),
                                                resultSet.getInt(4),
                            languageEntity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(resultSet != null)
                    resultSet.close();
                MySQLDAOFactory.closeConnection(connection);
                ;
            }
        }
        return entities;
    }
}
