package web.vasilizas.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.person.MyService.log;

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
        log.info("give session");
        String loginURI = "/myweb/auth";
        String  loginUri2 = "/myweb/home";
        String type;
        log.info("check session");
        if (session == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/myweb/home");
            requestDispatcher.forward(req, res);
            return;
            }
        else type = (String) session.getAttribute("type");
        log.info("check type");
        boolean loggedIn = admin.equals(type) || student.equals(type) || teacher.equals(type);
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loginUri2);

        if (loggedIn|| loginRequest) {
            log.info("The filter worked, the request was approved");
            chain.doFilter(request, response);
        } else {
            log.info("The filter worked, the request was redirected");
            response.sendRedirect("/myweb/home");
            return;
        }
    }

    @Override
    public void destroy() {
        //destroy
    }
}
