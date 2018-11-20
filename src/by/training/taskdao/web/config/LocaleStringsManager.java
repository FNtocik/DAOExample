package by.training.taskdao.web.config;

import by.training.taskdao.locale.LocaleManager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Locale manager for application, build on Singleton pattern
 * @author Anton Puhachou
 */
public class LocaleStringsManager {

    /**static field instance of manager*/
    private static LocaleStringsManager instance = null;

    /**field strings bundle*/
    private ResourceBundle webStringsResourceBundle;

    /**
     * Private constructor to create an instance of manager
     */
    private LocaleStringsManager(){
        Locale locale = LocaleManager.getInstance().getLocale();
        webStringsResourceBundle = ResourceBundle.getBundle("webstrings",
                                                            locale);
    }

    public void updateLocale(){
        Locale locale = LocaleManager.getInstance().getLocale();
        webStringsResourceBundle = ResourceBundle.getBundle("webstrings",
                                                            locale);
    }

    /**
     * Singleton pattern realisation
     * @return instance of manager
     */
    public static LocaleStringsManager getInstance() {
        if(instance == null)
            instance = new LocaleStringsManager();
        return instance;
    }

    /**
     * get locale strings for login page
     * @return strings in <b>json</b>
     */
    public String getLoginPageStrings(){
        return webStringsResourceBundle.getString("login");
    }

    /**
     * get locale strings for denied access page
     * @return strings in <b>json</b>
     */
    public String getDeniedPageStrings() {
        return webStringsResourceBundle.getString("denied");
    }
}
