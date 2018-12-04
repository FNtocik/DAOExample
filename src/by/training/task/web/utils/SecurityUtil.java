package by.training.task.web.utils;

import by.training.task.web.config.SecurityConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Security util class to check user permissions and security pages
 *
 * @author Anton Puhachou
 */
public class SecurityUtil {

    /**
     * Method to find current url in security pages list
     * @param url request url
     * @return true if url secured, false if url not secured
     * */
    public static boolean isSecurityPage(String url){
        SecurityConfig securityConfig = SecurityConfig.getInstance();
        Set<String> roles = securityConfig.getAllAppRoles();
        for(String currentRole : roles){
            List<String> urlsForRole = securityConfig.getUrlForRole(currentRole);
            if(urlsForRole != null && urlsForRole.contains(url)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method to definition if current request can pass to secured url
     * @param roleRequest request with set role
     * @param url secured url
     * @return true if request can be passed, false in another case
     * */
    public static boolean havePermissionToPage(HttpServletRequest roleRequest, String url) {
        SecurityConfig securityConfig = SecurityConfig.getInstance();
        Set<String> allRoles = securityConfig.getAllAppRoles();
        for(String currentRole : allRoles){
            if (!roleRequest.isUserInRole(currentRole)) {
                continue;
            }
            List<String> urlForRole = securityConfig.getUrlForRole(currentRole);
            if (urlForRole != null && urlForRole.contains(url)) {
                return true;
            }
        }
        return false;
    }
}
