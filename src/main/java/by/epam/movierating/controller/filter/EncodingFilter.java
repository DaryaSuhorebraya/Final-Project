package by.epam.movierating.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Даша on 17.02.2017.
 */
public class EncodingFilter implements Filter {
    private static final String ENCODING="UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
