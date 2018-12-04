package by.training.task.web.config;

import java.util.ResourceBundle;

/**
 * Manager of html template path, build on Singleton pattern
 *
 * @author Anton Puhachou
 */
public class TemplateFolderManager {

    /**static field instance of manager*/
    private static TemplateFolderManager instance = null;

    /**field template resource bundle*/
    private ResourceBundle templateBundle = null;

    /**private constructor*/
    private TemplateFolderManager() {
        templateBundle = ResourceBundle.getBundle("template");
    }

    /**
     * Singleton pattern realisation
     * @return instance of manager
     */
    public static TemplateFolderManager getInstance() {
        if (instance == null)
            instance = new TemplateFolderManager();
        return instance;
    }

    /**
     * Method of obtaining path to templates
     * @return global path to templates
     * */
    public String getPathInfo() {
        return templateBundle.getString("pathToTemplates");
    }
}
