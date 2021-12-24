package web.vasilizas.controller.servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admins/*")
public class AuthenticationAdminFilter extends AbstractFilter {

    private final Logger myLogger = LoggerFactory.getLogger(AuthenticationAdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        //init
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();

        boolean loggedLogin = session != null && session.getAttribute("type") != null;

        if (loggedLogin) {
            myLogger.info("check type admin filter");
            String userRole = (String) session.getAttribute("type");

            if (userRole.equals("Admin")) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/work");
                requestDispatcher.forward(req, res);
            }

        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/work");
            requestDispatcher.forward(req, res);
        }
    }

    @Override
    public void destroy() {
        //destroy
    }
}
