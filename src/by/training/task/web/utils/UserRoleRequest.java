package by.training.task.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

/**
 * Wrapper of request with logged user login and role
 *
 * @author Anton Puhachou
 */
public class UserRoleRequest extends HttpServletRequestWrapper {

    /**field logged user login*/
    private String login;

    /**field logged user role*/
    private String role;

    /**field request*/
    private HttpServletRequest request;

    /**
     * Constructor to create wrapper
     * @param login logged user login
     * @param role logged user role
     * @param request request
     * */
    public UserRoleRequest(String login, String role, HttpServletRequest request) {
        super(request);
        this.login = login;
        this.role = role;
        this.request = request;
    }

    /**
     * Method to check is user in role
     * @param role role to check
     * @return true if user in current role, false in another case
     * */
    @Override
    public boolean isUserInRole(String role) {
        return this.role.equals(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if(this.login == null) {
            return request.getUserPrincipal();
        }
        return new Principal() {
            @Override
            public String getName() {
                return login;
            }
        };
    }
}
