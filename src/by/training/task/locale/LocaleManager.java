package by.training.task.locale;

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

    /**
     * Method of obtaining supported in application language
     *
     * @return language signature
     */
    public String getSupportedLanguage(String signature) {
        if (localeResourceBundle.keySet().contains(signature))
            return localeResourceBundle.getString(signature);
        else
            return getDefaultLanguage();
    }

    /**
     * Method of obtaining default language in application
     * @return default language signature
     * */
    public String getDefaultLanguage() {
        return localeResourceBundle.getString("default");
    }

    /**
     * Method of obtaining path to locale string of application
     * @return global path
     * */
    public String getPathToLocale() {
        return localeResourceBundle.getString("langDirectory");
    }
}
