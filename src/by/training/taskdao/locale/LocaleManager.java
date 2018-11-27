package by.training.taskdao.locale;

import java.util.ResourceBundle;

/**
 * Locale manager for application, build on Singleton pattern
 * @author Anton Puhachou
 */
public class LocaleManager {

    /**field config bundle*/
    private ResourceBundle localeResourceBundle;

    /**static field instance of manager*/
    private static LocaleManager instance = null;

    /**
     * Private constructor to create an instance of manager
     */
    private LocaleManager(){
        localeResourceBundle = ResourceBundle.getBundle("locale");
    }

    /**
     * Singleton pattern realisation
     * @return instance of manager
     */
    public static LocaleManager getInstance(){
        if(instance == null)
            instance = new LocaleManager();
        return instance;
    }

    public String getSupportedLanguage(String signature) {
        if (localeResourceBundle.keySet().contains(signature))
            return localeResourceBundle.getString(signature);
        else
            return localeResourceBundle.getString("default");
    }

    public String getDefaultLanguage() {
        return localeResourceBundle.getString("default");
    }
}
