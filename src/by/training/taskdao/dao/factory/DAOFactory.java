package by.training.taskdao.dao.factory;

import by.training.taskdao.dao.interfaces.*;
import by.training.taskdao.dao.mysql.factory.MySQLDAOFactory;

/**
 * Abstract DAO factory pattern realization
 * @author Anton Puhachou
* */

public abstract class DAOFactory {

    /**constants that reflect the selected base*/
    public static final int MYSQL = 1;

    /**
     * abstract function of accessing the table administrator
     * @return data access class
     */
    public abstract AdministratorDAO getAdministratorDAO();

    /**
     * abstract function of accessing the table payment
     * @return data access class
     */
    public abstract PaymentDAO getPaymentDAO();

    /**
     * abstract function of accessing the table publication
     * @return data access class
     */
    public abstract PublicationDAO getPublicationDAO();

    /**
     * abstract function of accessing the table reader
     * @return data access class
     */
    public abstract ReaderDAO getReaderDAO();

    /**
     * abstract function of accessing the table subscription
     * @return data access class
     */
    public abstract SubscriptionDAO getSubscriptionDAO();

    /**
     * abstract function of accessing the table language
     * @return data access class
     */
    public abstract LanguageDAO getLanguageDAO();

    /**
     * abstract factory method of creating a concrete factory
     * @param whichFactory represents witch factory to return
     * @return a specific factory
     */
    public static DAOFactory getDAOFactory(int whichFactory){
        switch (whichFactory){
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
