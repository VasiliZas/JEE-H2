package web.vasilizas.controller.authentication;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/myweb/admin/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        //Существует ли сессиия
        boolean loggedLogin = session != null && session.getAttribute("login") != null && session.getAttribute("type") != null;
        if (loggedLogin) {
            //Если существует то получаем роль
            String userRole = session.getAttribute("type").toString();
            if (userRole.equals("Student")) {
                response.sendRedirect(request.getContextPath() + "/student/student.jsp");
            } else if (userRole.equals("Admin")) {
                response.sendRedirect(request.getContextPath() + "/myweb/admin.jsp");
            } else if (userRole.equals("Teacher")) {
                response.sendRedirect(request.getContextPath() + "/teacher/teacher.jsp");
            }
            //Если нет то на страницу входа.
        } else response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    public void destroy() {

    }
}
