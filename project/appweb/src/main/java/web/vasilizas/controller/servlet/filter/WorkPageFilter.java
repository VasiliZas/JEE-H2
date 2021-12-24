package web.vasilizas.controller.servlet.filter;

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

@WebFilter(filterName = "WorkPageFilter", urlPatterns = "/myweb")
public class WorkPageFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) {
//init
    }

    @Override
    public void destroy() {
//destroy
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String type = (String) session.getAttribute("type");

        switch (type) {
            case "Student" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/students/student");
                requestDispatcher.forward(req, resp);
                chain.doFilter(request, response);
            }
            case "Teacher" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teachers/teacher");
                requestDispatcher.forward(req, resp);
                chain.doFilter(request, response);
            }
            case "Admin" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admins/admin");
                requestDispatcher.forward(req, resp);
                chain.doFilter(request, response);
            }
            default -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/about");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}

