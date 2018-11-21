package by.training.taskdao.web.filter;

import by.training.taskdao.entities.LoggedUser;
import by.training.taskdao.web.utils.SecurityUtil;
import by.training.taskdao.web.utils.SessionUtil;
import by.training.taskdao.web.utils.UserRoleRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security filter to control page and servlets requests and prevent
 * unauthorized access
 * @author Anton Puhachou
 */
@WebFilter("/secure/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String servletPath = request.getServletPath().substring(1);
        if(servletPath.equals("login.html")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        boolean isServlet = !servletPath.contains(".");
        if(isServlet) {
            servletPath += " " + request.getMethod().toLowerCase();
        }
        LoggedUser loggedUser = SessionUtil.getLoggedUserFromSession(request.getSession());
        HttpServletRequest roleRequest = request;
        if(loggedUser != null){
            String login = loggedUser.getLogin();
            String role = loggedUser.getRole();
            roleRequest = new UserRoleRequest(login, role, request);
        }
        boolean isSecurityPage;
        if(isServlet) {
            isSecurityPage = SecurityUtil.isSecurityServlet(servletPath.split(" "));
        } else {
            isSecurityPage = SecurityUtil.isSecurityPage(servletPath);
        }
        if(isSecurityPage){
            if(loggedUser == null){
                response.sendRedirect(request.getContextPath() + "/login.html");
                return;
            }
            boolean isHavePermission;
            if(isServlet) {
                isHavePermission = SecurityUtil.havePermissionToServlet(roleRequest, servletPath.split(" "));
            } else {
                isHavePermission = SecurityUtil.havePermissionToPage(roleRequest, servletPath);
            }
            if(!isHavePermission){
                response.sendRedirect(request.getContextPath() + "/denied.html");
                return;
            }
        }
        filterChain.doFilter(roleRequest, response);
    }

    @Override
    public void destroy() {

    }
}
