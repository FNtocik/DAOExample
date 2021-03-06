package by.training.task.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Encode filter to set character encode in the request and response
 * @author Anton Puhachou
 */
@WebFilter(filterName = "EncodeFilter")
public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getCharacterEncoding() == null){
            servletRequest.setCharacterEncoding("UTF-8");
        }
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
