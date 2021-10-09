package web.vasilizas.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        String type;

        if (session == null) {
            type = "null";
        } else type = (String) session.getAttribute("type");

        boolean loggedIn = admin.equals(type) || student.equals(type) || teacher.equals(type);
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/myweb/auth.html");
        }
    }

    @Override
    public void destroy() {
        //destroy
    }
}
