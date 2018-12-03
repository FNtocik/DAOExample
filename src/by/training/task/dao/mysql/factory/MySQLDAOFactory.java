package by.training.task.dao.mysql.factory;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.*;
import by.training.task.dao.mysql.config.ConfigurationManager;
import by.training.task.dao.mysql.implementation.*;
import by.training.task.dao.mysql.pool.ConnectionPool;

import java.sql.Connection;

/**
 * Specific DAO factory pattern realization to the MySQL database
 * @author Anton Puhachou
 * @see by.training.task.dao.factory.DAOFactory
 * */
public class MySQLDAOFactory extends DAOFactory {

    /**
     * Instance of connection pool to MySQL database
     *
     * @see ConnectionPool
     */
    private static ConnectionPool connectionPool = null;

    /**
     * database connection creation method
     * @return connection to database
     */
    public static Connection createConnection(){
        if (connectionPool == null) {
            ConfigurationManager configurationManager = ConfigurationManager.getInstance();
            String driver = configurationManager.getDriver();
            String dburl = configurationManager.getConnectionString();
            String login = configurationManager.getLogin();
            String password = configurationManager.getPassword();
            connectionPool = new ConnectionPool(dburl, driver, login, password);
        }
        return connectionPool.getConnection();
    }

    public static void closeConnection(Connection connection) {
        connectionPool.removeConnection(connection);
    }

    /**
     * @see DAOFactory#getAdministratorDAO()
     * @return data access class
     */
    @Override
    public AdministratorDAO getAdministratorDAO() {
        return new MySQLAdministratorDAO();
    }

    /**
     * @see DAOFactory#getPaymentDAO()
     * @return data access class
     */
    @Override
    public PaymentDAO getPaymentDAO() {
        return new MySQLPaymentDAO();
    }

    /**
     * @see DAOFactory#getPublicationDAO()
     * @return data access class
     */
    @Override
    public PublicationDAO getPublicationDAO() {
        return new MySQLPublicationDAO();
    }

    /**
     * @see DAOFactory#getReaderDAO()
     * @return data access class
     */
    @Override
    public ReaderDAO getReaderDAO() {
        return new MySQLReaderDAO();
    }

    /**
     * @see DAOFactory#getSubscriptionDAO()
     * @return data access class
     */
    @Override
    public SubscriptionDAO getSubscriptionDAO() {
        return new MySQLSubscriptionDAO();
    }

    /**
     * @see DAOFactory#getLanguageDAO()
     * @return data access class
     */
    @Override
    public LanguageDAO getLanguageDAO(){
        return new MySQLLanguageDAO();
    }
}
