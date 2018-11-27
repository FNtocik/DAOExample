package by.training.taskdao.web.utils;

import by.training.taskdao.web.config.SecurityConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtil {
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

    public static boolean havePermissionToPage(HttpServletRequest roleRequest, String url) {
        SecurityConfig securityConfig = SecurityConfig.getInstance();
        Set<String> allRoles = securityConfig.getAllAppRoles();
        for(String currentRole : allRoles){
            if(!roleRequest.isUserInRole(currentRole))
                continue;
            List<String> urlForRole = securityConfig.getUrlForRole(currentRole);
            if(urlForRole != null && urlForRole.contains(url))
                return true;
        }
        return false;
    }
}
