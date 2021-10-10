package web.vasilizas.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) {
//init
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String admin = "Admin";
        String student = "Student";
        String teacher = "Teacher";

        HttpSession session = request.getSession();

        String loginURI = "/myweb/auth";
        String loginUri2 = "/myweb/start";
        String type;

        if (session == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(loginUri2);
            requestDispatcher.forward(req, res);
            return;
        } else type = (String) session.getAttribute("type");

        boolean loggedIn = admin.equals(type) || student.equals(type) || teacher.equals(type);
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loginUri2);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            myLogger.info("The LoginFilter worked, the request was redirected");
            response.sendRedirect(loginUri2);
            return;
        }
    }

    @Override
    public void destroy() {
        //destroy
    }
}
