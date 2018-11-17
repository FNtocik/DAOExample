package by.training.taskdao.web.locale;

import by.training.taskdao.locale.LocaleManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleStringsManager {

    private static LocaleStringsManager instance = null;

    private ResourceBundle webStringsResourceBundle;

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

    public static LocaleStringsManager getInstance() {
        if(instance == null)
            instance = new LocaleStringsManager();
        return instance;
    }

    public String getLoginPageStrings(){
        return webStringsResourceBundle.getString("login");
    }
}
