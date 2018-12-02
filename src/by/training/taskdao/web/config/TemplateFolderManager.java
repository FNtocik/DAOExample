package by.training.taskdao.web.config;

import java.util.ResourceBundle;

public class TemplateFolderManager {

    private static TemplateFolderManager instance = null;

    private ResourceBundle templateBundle = null;

    private TemplateFolderManager() {
        templateBundle = ResourceBundle.getBundle("template");
    }

    public static TemplateFolderManager getInstance() {
        if (instance == null)
            instance = new TemplateFolderManager();
        return instance;
    }

    public String getPathInfo() {
        return templateBundle.getString("pathToTemplates");
    }
}
