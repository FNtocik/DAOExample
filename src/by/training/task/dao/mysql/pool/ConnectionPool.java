package by.training.task.dao.mysql.pool;

import by.training.task.utils.LoggerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realisation of connection pool to MySQL database
 *
 * @author Anton Puhachou
 */
public class ConnectionPool {

    /**constant of max connection number*/
    private static final int MAX_CONNECTIONS = 10;

    /**field list of available connections*/
    private List<Connection> connectionPool;

    /**field list of connections in use*/
    private List<Connection> usedConnections;

    /**
     * Constructor to create connection pool to database
     * @param url database url
     * @param driver driver to connect
     * @param login database login
     * @param password database password
     * */
    public ConnectionPool(String url, String driver, String login, String password) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionPool = new ArrayList<>();
        usedConnections = new ArrayList<>();
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                connectionPool.add(DriverManager.getConnection(url, login, password));
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(ConnectionPool.class.toString(), e);
            }
        }
    }

    /**
     * Synchronized method to obtain connection if it available
     * @return connection to database
     * @throws RuntimeException if there no available connections
     * */
    public synchronized Connection getConnection() throws RuntimeException {
        Connection newConnection = null;
        if (connectionPool.size() > 0) {
            newConnection = connectionPool.remove(0);
            usedConnections.add(newConnection);
            return newConnection;
        } else {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.info("All connections in use");
            throw new RuntimeException("All connection in use");
        }
    }

    /**
     * Synchronized method of returning connection to the pool
     * @throws NullPointerException if connection not from this pool
     * */
    public synchronized void removeConnection(Connection connection) throws NullPointerException {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                connectionPool.add(connection);
            } else {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.info("Connection not from connection pool");
                throw new NullPointerException("Connection not from connection pool");
            }
        }
    }
}
