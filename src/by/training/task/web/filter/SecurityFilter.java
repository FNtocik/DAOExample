package by.training.task.web.filter;

import by.training.task.entities.LoggedUser;
import by.training.task.utils.LoggerManager;
import by.training.task.web.config.SecurityConfig;
import by.training.task.web.utils.SecurityUtil;
import by.training.task.web.utils.SessionUtil;
import by.training.task.web.utils.UserRoleRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security filter to control page and servlets requests and prevent unauthorized access
 * @author Anton Puhachou
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String servletPath = request.getServletPath().substring(1);
        boolean isSecureServlet = servletPath.contains("secure/");
        servletPath = servletPath.replace("secure/", "");
        if (servletPath.contains("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        LoggedUser loggedUser = SessionUtil.getLoggedUserFromSession(request.getSession());
        HttpServletRequest roleRequest = request;
        if(loggedUser != null){
            String login = loggedUser.getLogin();
            String role = loggedUser.getRole();
            roleRequest = new UserRoleRequest(login, role, request);
        } else {
            loggedUser = new LoggedUser("", "", SecurityConfig.ROLE_GUEST);
            roleRequest = new UserRoleRequest("", SecurityConfig.ROLE_GUEST, request);
        }
        if (SecurityUtil.isSecurityPage(servletPath)) {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.info(this.getClass().toString() + " user try to access: " + loggedUser.getLogin() + " Role: " +
                    loggedUser.getRole() + " URL: " + servletPath);
            if (!SecurityUtil.havePermissionToPage(roleRequest, servletPath)) {
                response.sendRedirect(request.getContextPath() + "/denied.html");
                return;
            } else if (!isSecureServlet) {
                request.getRequestDispatcher(servletPath + loggedUser.getRole()).forward(request, servletResponse);
            }
        }
        filterChain.doFilter(roleRequest, response);
    }

    @Override
    public void destroy() {

    }
}
