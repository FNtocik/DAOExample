package by.training.task.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequest extends HttpServletRequestWrapper {

    private String login;

    private String role;

    private HttpServletRequest request;

    public UserRoleRequest(String login, String role, HttpServletRequest request) {
        super(request);
        this.login = login;
        this.role = role;
        this.request = request;
    }

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
