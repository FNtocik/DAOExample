package by.training.taskdao.locale;

import by.training.taskdao.dao.mysql.config.ConfigurationManager;
import by.training.taskdao.web.config.LocaleStringsManager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Locale manager for application, build on Singleton pattern
 * @author Anton Puhachou
 */
public class LocaleManager {

    /**constants that reflect the selected en config*/
    public static final int EN_LOCALE = 1;

    /**constants that reflect the selected ru config*/
    public static final int RU_LOCALE = 2;

    /**field current config*/
    private Locale locale;

    /**field config bundle*/
    private ResourceBundle localeResourceBundle;

    /**static field instance of manager*/
    private static LocaleManager instance = null;

    /**
     * Private constructor to create an instance of manager
     */
    private LocaleManager(){
        localeResourceBundle = ResourceBundle.getBundle("locale");
        locale = new Locale(localeResourceBundle.getString("default"));
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
     * get current config
     * @return current config
     */
    public Locale getLocale(){
        return locale;
    }

    /**
     * set current config
     * @param which represents witch config to set
     * @see #EN_LOCALE
     * @see #RU_LOCALE
     */
    public void setLocale(int which){
        switch (which){
            case EN_LOCALE:
                locale = new Locale(localeResourceBundle.getString("en"));
                break;
            case RU_LOCALE:
                locale = new Locale(localeResourceBundle.getString("ru"));
                break;
            default:
                locale = new Locale(localeResourceBundle.getString("default"));
                break;
        }
        ConfigurationManager.getInstance().updateLocale();
        LocaleStringsManager.getInstance().updateLocale();
    }
}
