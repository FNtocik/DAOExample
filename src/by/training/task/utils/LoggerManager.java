package by.training.task.utils;

import org.apache.log4j.Logger;

/**
 * Logger manager that provides log4j system to application, build on Singleton pattern
 *
 * @author Anton Puhachou
 */
public class LoggerManager {

    /**
     * static field instance of manager
     */
    private static LoggerManager instance = null;

    /**
     * field logger
     */
    private Logger logger = null;

    /**
     * private constructor with logger creation
     */
    private LoggerManager() {
        logger = Logger.getLogger(LoggerManager.class);
    }

    /**
     * Singleton pattern realisation
     *
     * @return instance of manager
     */
    public static LoggerManager getInstance() {
        if (instance == null) {
            instance = new LoggerManager();
        }
        return instance;
    }

    /**
     * Method to log error
     *
     * @param message submessage
     * @param ex      exception to log
     */
    public void error(String message, Exception ex) {
        logger.error(message, ex);
    }

    /**
     * Method to log info
     *
     * @param message message to log
     */
    public void info(String message) {
        logger.info(message);
    }
}
