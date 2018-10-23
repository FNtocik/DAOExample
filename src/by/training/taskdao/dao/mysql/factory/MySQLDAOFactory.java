package by.training.taskdao.dao.mysql.factory;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.*;
import by.training.taskdao.dao.mysql.config.ConfigurationManager;
import by.training.taskdao.dao.mysql.implementation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Specific DAO factory pattern realization to the MySQL database
 * @author Anton Puhachou
 * */
public class MySQLDAOFactory extends DAOFactory {

    /**
     * database connection creation method
     * @return connection to database
     */
    public static Connection createConnection(){
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        try {
            Class.forName(configurationManager.getDriver());
            String dburl = configurationManager.getConnectionString();
            String login = configurationManager.getLogin();
            String password = configurationManager.getPassword();
            return DriverManager.getConnection(dburl, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
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
