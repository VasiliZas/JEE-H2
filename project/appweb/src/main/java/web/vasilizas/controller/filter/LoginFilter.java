package web.vasilizas.controller.filter;

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

import static web.vasilizas.UrlRepository.urlMap;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter extends AbstractFilter {

    private final Logger myLogger = LoggerFactory.getLogger(LoginFilter.class);

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

        String loginURI = urlMap.get("Auth");
        String loginUri2 = urlMap.get("Start");
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
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/start");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        //destroy
    }
}
