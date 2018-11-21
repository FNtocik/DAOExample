package by.training.taskdao.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cache filter to set headers in the response that prevent caching
 * @author Anton Puhachou
 */
@WebFilter("/secure/*")
public class CacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Cache-Control", "must-revalidate");
        resp.setDateHeader("Expires", 0);
        filterChain.doFilter(servletRequest, resp);
    }

    @Override
    public void destroy() {

    }
}
