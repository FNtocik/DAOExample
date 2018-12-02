package by.training.taskdao.web.config;

import java.util.*;

/**
 * Security config class with provided roles and access urls for each, based
 * on Singleton pattern
 * @author Anton Puhachou
 */
public class SecurityConfig {

    /**constant for reader role name*/
    public static final String ROLE_READER = "Reader";

    /**constant for administrator role name*/
    public static final String ROLE_ADMINISTRATOR = "Administrator";

    /**
     * constant for administrator role name
     */
    public static final String ROLE_GUEST = "Guest";

    /**HashMap for storage urls for each role in system*/
    private final Map<String, List<String>> mapConfig = new HashMap<>();

    /**static field instance of manager*/
    private static SecurityConfig instance = null;

    /**field urls properties bundle*/
    private ResourceBundle roleResourceBundle;

    /**
     * private constructor to create an instance of manager
     */
    private SecurityConfig(){
        roleResourceBundle = ResourceBundle.getBundle("access");
    }

    /**
     * urls initialization for each role
     */
    private void init(){
        String readerUrls = roleResourceBundle.getString(ROLE_READER);
        String administratorUrls = roleResourceBundle.getString(ROLE_ADMINISTRATOR);
        String guestUrls = roleResourceBundle.getString(ROLE_GUEST);
        setToMap(ROLE_READER, readerUrls);
        setToMap(ROLE_ADMINISTRATOR, administratorUrls);
        setToMap(ROLE_GUEST, guestUrls);
    }

    /**
     * set to HashMap for current role specified urls
     * @param roleName name of role to set
     * @param urls specified to this role urls
     */
    private void setToMap(String roleName, String urls){
        String[] splited = urls.split(", ");
        List<String> urlList = new ArrayList<>();
        Collections.addAll(urlList, splited);
        mapConfig.put(roleName, urlList);
    }

    /**
     * Singleton pattern realisation
     * @return instance of manager
     */
    public static SecurityConfig getInstance() {
        if(instance == null) {
            instance = new SecurityConfig();
            instance.init();
        }
        return instance;
    }

    /**
     * get method of all role names in HashMap
     * @return set of role names
     */
    public Set<String> getAllAppRoles(){
        return mapConfig.keySet();
    }

    /**
     * get method of all urls for given role
     * @param role name of role
     * @return list of urls, that specified to given role
     */
    public List<String> getUrlForRole(String role){
        return mapConfig.get(role);
    }
}
