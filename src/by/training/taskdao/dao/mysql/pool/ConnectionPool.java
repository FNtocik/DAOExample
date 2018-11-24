package by.training.taskdao.dao.mysql.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int MAX_CONNECTIONS = 10;

    private List<Connection> connectionPool;
    private List<Connection> usedConnections;

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
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection() throws RuntimeException {
        Connection newConnection = null;
        if (connectionPool.size() > 0) {
            newConnection = connectionPool.remove(0);
            usedConnections.add(newConnection);
            return newConnection;
        } else {
            throw new RuntimeException("All connection in use");
        }
    }

    public synchronized void removeConnection(Connection connection) throws NullPointerException {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                connectionPool.add(connection);
            } else {
                throw new NullPointerException("Connection not from connection pool");
            }
        }
    }
}
