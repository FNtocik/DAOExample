package by.training.task.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO layer generic interface for tables with basic operations
 *
 * @author Anton Puhachou
 */
public interface GenericDAO<T> {

    /**
     * Method of obtaining a specific object from database by id
     * @return found object or null
     * */
    T get(int id) throws SQLException;

    /**
     * Method of adding object to the database
     * @param entity object to add in database
     * @return id of created entity
     * */
    int create(T entity) throws SQLException;

    /**
     * Method of updating entity in database
     * @param entity object with new information
     * @return id of updated entity
     * */
    int update(T entity) throws SQLException;

    /**
     * Method of deleting entity in database
     * @param id id of entity that need to be deleted
     * @return id of deleted entity
     * */
    int delete(int id) throws SQLException;

    /**
     * Method of getting all entities from database
     * @return list of objects
     * */
    List<T> getAll() throws SQLException;
}
