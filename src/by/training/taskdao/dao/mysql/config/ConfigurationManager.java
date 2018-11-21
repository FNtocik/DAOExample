package by.training.taskdao.dao.mysql.config;

import java.util.ResourceBundle;

/**
 * Configuration manager for MySQL database configuration and queries, build
 * on Singleton pattern
 * @author Anton Puhachou
 */
public class ConfigurationManager {

    /**field database configuration bundle*/
    private ResourceBundle dbConfigResourceBundle;

    /**field database queries bundle*/
    private ResourceBundle mysqlQueryResourceBundle;

    /**static field instance of manager*/
    private static ConfigurationManager instance = null;

    /**
     * Private constructor to create an instance of manager with localisation
     * queries to database
     */
    private ConfigurationManager() {
        dbConfigResourceBundle = ResourceBundle.getBundle("dbconfig");
        mysqlQueryResourceBundle = ResourceBundle.getBundle("mysql_query");
    }

    /**
     * Singleton pattern realisation
     * @return instance of manager
     */
    public static ConfigurationManager getInstance() {
        if (instance == null)
            instance = new ConfigurationManager();
        return instance;
    }

    /**
     * get driver string from properties
     * @return driver string
     */
    public String getDriver() {
        return dbConfigResourceBundle.getString("driver");
    }

    /**
     * get connection to MySQL database string from properties
     * @return connection string
     */
    public String getConnectionString() {
        return dbConfigResourceBundle.getString("dburl");
    }

    /**
     * get login to MySQL database from properties
     * @return login to database
     */
    public String getLogin() {
        return dbConfigResourceBundle.getString("login");
    }

    /**
     * get password to MySQL database from properties
     * @return password to database
     */
    public String getPassword() {
        return dbConfigResourceBundle.getString("pass");
    }

    /**
     * get SQL get query to specialized administrator entity from properties
     * @return SQL query
     */
    public String getMySQLQueryAdminGet() {
        return mysqlQueryResourceBundle.getString("admin_get");
    }

    /**
     * get SQL create query for administrator entity from properties
     * @return SQL query
     */
    public String getMySQLQueryAdminCreate() {
        return mysqlQueryResourceBundle.getString("admin_create");
    }

    /**
     * get SQL update query for administrator entity from properties
     * @return SQL query
     */
    public String getMySQLQueryAdminUpdate() {
        return mysqlQueryResourceBundle.getString("admin_update");
    }

    /**
     * get SQL delete query for administrator entity from properties
     * @return SQL query
     */
    public String getMySQLQueryAdminDelete() {
        return mysqlQueryResourceBundle.getString("admin_delete");
    }

    /**
     * get SQL get all query for administrator table from properties
     * @return SQL query
     */
    public String getMySQLQueryAdminGetAll() {
        return mysqlQueryResourceBundle.getString("admin_get_all");
    }

    /**
     * get SQL get query to specialized payment entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPaymentGet() {
        return mysqlQueryResourceBundle.getString("payment_get");
    }

    /**
     * get SQL create query for payment entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPaymentCreate() {
        return mysqlQueryResourceBundle.getString("payment_create");
    }

    /**
     * get SQL update query for payment entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPaymentUpdate() {
        return mysqlQueryResourceBundle.getString("payment_update");
    }

    /**
     * get SQL delete query for payment entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPaymentDelete() {
        return mysqlQueryResourceBundle.getString("payment_delete");
    }

    /**
     * get SQL get all query for payment table from properties
     * @return SQL query
     */
    public String getMySQLQueryPaymentGetAll() {
        return mysqlQueryResourceBundle.getString("payment_get_all");
    }

    /**
     * get SQL get query to specialized publication entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPublicationGet() {
        return mysqlQueryResourceBundle.getString("publication_get");
    }

    /**
     * get SQL create query for publication entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPublicationCreate() {
        return mysqlQueryResourceBundle.getString("publication_create");
    }

    /**
     * get SQL update query for publication entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPublicationUpdate() {
        return mysqlQueryResourceBundle.getString("publication_update");
    }

    /**
     * get SQL delete query for publication entity from properties
     * @return SQL query
     */
    public String getMySQLQueryPublicationDelete() {
        return mysqlQueryResourceBundle.getString("publication_delete");
    }

    /**
     * get SQL get all query for publication table from properties
     * @return SQL query
     */
    public String getMySQLQueryPublicationGetAll() {
        return mysqlQueryResourceBundle.getString("publication_get_all");
    }

    /**
     * get SQL get query to specialized reader entity from properties
     * @return SQL query
     */
    public String getMySQLQueryReaderGet() {
        return mysqlQueryResourceBundle.getString("reader_get");
    }

    /**
     * get SQL create query for reader entity from properties
     * @return SQL query
     */
    public String getMySQLQueryReaderCreate() {
        return mysqlQueryResourceBundle.getString("reader_create");
    }

    /**
     * get SQL update query for reader entity from properties
     * @return SQL query
     */
    public String getMySQLQueryReaderUpdate() {
        return mysqlQueryResourceBundle.getString("reader_update");
    }

    /**
     * get SQL delete query for reader entity from properties
     * @return SQL query
     */
    public String getMySQLQueryReaderDelete() {
        return mysqlQueryResourceBundle.getString("reader_delete");
    }

    /**
     * get SQL get all query for reader table from properties
     * @return SQL query
     */
    public String getMySQLQueryReaderGetAll() {
        return mysqlQueryResourceBundle.getString("reader_get_all");
    }

    /**
     * get SQL get query to specialized subscription entity from properties
     * @return SQL query
     */
    public String getMySQLQuerySubscriptionGet() {
        return mysqlQueryResourceBundle.getString("subscription_get");
    }

    /**
     * get SQL create query for subscription entity from properties
     * @return SQL query
     */
    public String getMySQLQuerySubscriptionCreate() {
        return mysqlQueryResourceBundle.getString("subscription_create");
    }

    /**
     * get SQL update query for subscription entity from properties
     * @return SQL query
     */
    public String getMySQLQuerySubscriptionUpdate() {
        return mysqlQueryResourceBundle.getString("subscription_update");
    }

    /**
     * get SQL delete query for subscription entity from properties
     * @return SQL query
     */
    public String getMySQLQuerySubscriptionDelete() {
        return mysqlQueryResourceBundle.getString("subscription_delete");
    }

    /**
     * get SQL get all query for subscription table from properties
     * @return SQL query
     */
    public String getMySQLQuerySubscriptionGetAll() {
        return mysqlQueryResourceBundle.getString("subscription_get_all");
    }

    /**
     * get SQL get query to specialized language entity from properties
     * @return SQL query
     */
    public String getMySQLQueryLanguageGet() {
        return mysqlQueryResourceBundle.getString("language_get");
    }

    /**
     * get SQL create query for language entity from properties
     * @return SQL query
     */
    public String getMySQLQueryLanguageCreate() {
        return mysqlQueryResourceBundle.getString("language_create");
    }

    /**
     * get SQL update query for language entity from properties
     * @return SQL query
     */
    public String getMySQLQueryLanguageUpdate() {
        return mysqlQueryResourceBundle.getString("language_update");
    }

    /**
     * get SQL delete query for language entity from properties
     * @return SQL query
     */
    public String getMySQLQueryLanguageDelete() {
        return mysqlQueryResourceBundle.getString("language_delete");
    }

    /**
     * get SQL get all query for language table from properties
     * @return SQL query
     */
    public String getMySQLQueryLanguageGetAll() {
        return mysqlQueryResourceBundle.getString("language_get_all");
    }
}
