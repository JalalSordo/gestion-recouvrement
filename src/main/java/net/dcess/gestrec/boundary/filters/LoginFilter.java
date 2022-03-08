package net.dcess.gestrec.boundary.filters;

/**
 *
 * @author jalal sordo
 */
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.dcess.gestrec.boundary.LoginBacking;

@Slf4j
@WebFilter
public class LoginFilter implements Filter {

    @Inject
    private LoginBacking auth;

    public static final String LOGIN_PAGE = "/login.xhtml";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        String loginURL = request.getContextPath() + LOGIN_PAGE;

        boolean loggedIn = auth != null && auth.isLoggedIn();
        boolean loginRequest = request.getRequestURI().equals(loginURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

        if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            }

            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURL);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.debug("loginFilter initialized");
    }

    @Override
    public void destroy() {
        // close resources
    }
}
