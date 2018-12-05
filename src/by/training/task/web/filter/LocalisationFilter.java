package by.training.task.web.filter;

import by.training.task.locale.LocaleManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


/**
 * Localisation filter to set user locale in cookies and request attribute
 *
 * @author Anton Puhachou
 */
@WebFilter(filterName = "LocalisationFilter")
public class LocalisationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

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
                }
            }
        }
        if (local == null) {
            Locale userLocale = request.getLocale();
            local = localeManager.getSupportedLanguage(userLocale.getLanguage());
        }
        response.addCookie(new Cookie("local", local));
        request.setAttribute("locale", local);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
