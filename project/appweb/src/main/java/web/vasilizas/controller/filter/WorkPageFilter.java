package web.vasilizas.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "WorkPageFilter", urlPatterns = "/myweb")
public class WorkPageFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String type = (String) session.getAttribute("type");

        switch (type) {
            case "Student" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student");
                requestDispatcher.forward(req, resp);
                chain.doFilter(request, response);
            }
            case "Teacher" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher");
                requestDispatcher.forward(req, resp);
                chain.doFilter(request, response);
            }
            case "Admin" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
                requestDispatcher.forward(req, resp);
                chain.doFilter(request, response);
            }
            default -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/start");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}

