package by.training.taskdao.web.config;

import java.util.*;

public class SecurityConfig {

    public static final String ROLE_READER = "READER";
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";

    private final Map<String, List<String>> mapConfig = new HashMap<>();

    private static SecurityConfig instance = null;

    private ResourceBundle roleResourceBundle;

    private SecurityConfig(){
        roleResourceBundle = ResourceBundle.getBundle("access");
    }

    private void init(){
        String readerUrls = roleResourceBundle.getString(ROLE_READER);
        String administratorUrls = roleResourceBundle.getString(ROLE_ADMINISTRATOR);
        setToMap(ROLE_READER, readerUrls);
        setToMap(ROLE_ADMINISTRATOR, administratorUrls);
    }

    private void setToMap(String roleName, String urls){
        String[] splited = urls.split(", ");
        List<String> urlList = new ArrayList<>();
        Collections.addAll(urlList, splited);
        mapConfig.put(roleName, urlList);
    }

    public static SecurityConfig getInstance() {
        if(instance == null) {
            instance = new SecurityConfig();
            instance.init();
        }
        return instance;
    }

    public Set<String> getAllAppRoles(){
        return mapConfig.keySet();
    }

    public List<String> getUrlForRole(String role){
        return mapConfig.get(role);
    }
}
