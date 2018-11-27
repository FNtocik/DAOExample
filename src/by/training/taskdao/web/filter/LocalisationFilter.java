package by.training.taskdao.web.filter;

import by.training.taskdao.locale.LocaleManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class LocalisationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LocaleManager localeManager = LocaleManager.getInstance();
        String local = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie current : cookies) {
                if (current.getName().equalsIgnoreCase("local")) {
                    local = current.getValue();
                    break;
                }
            }
        }
        if (local == null) {
            Locale userLocale = request.getLocale();
            local = localeManager.getSupportedLanguage(userLocale.getLanguage());
        }
        response.addCookie(new Cookie("local", local));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
