package web.vasilizas.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebFilter(urlPatterns = "/teacher/*")
public class AuthenticationTeacherFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) {
        //init filter
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();

        boolean loggedLogin = session != null && session.getAttribute("type") != null;

        if (loggedLogin) {
            myLogger.info("check type teacher filter");
            String userRole = (String) session.getAttribute("type");

            if (userRole.equals("Teacher")) {
                chain.doFilter(request, response);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home");
                requestDispatcher.forward(req, res);
            }

        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home");
            requestDispatcher.forward(req, res);
        }
    }

    @Override
    public void destroy() {
        //destroy filter
    }
}
