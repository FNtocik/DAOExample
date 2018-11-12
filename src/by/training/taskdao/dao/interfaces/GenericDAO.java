package by.training.taskdao.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    T get(int id) throws SQLException;
    int create(T entity) throws SQLException;
    int update(T entity) throws SQLException;
    int delete(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}
