package by.training.taskdao.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

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
}
