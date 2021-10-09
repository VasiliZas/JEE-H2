//package web.vasilizas.controller.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/myweb/admin/*")
//public class AuthenticationFilter extends AbstractFilter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession();
//
//        //Существует ли сессиия
//        boolean loggedLogin = session != null && session.getAttribute("type") != null;
//        if (loggedLogin) {
//            //Если существует то получаем роль
//            String userRole = session.getAttribute("type").toString();
//            if (userRole.equals("Student")) {
//                response.sendRedirect( "/index.html");
//            } else if (userRole.equals("Admin")) {
//                response.sendRedirect("/myweb/admin/admin.html");
//            } else if (userRole.equals("Teacher")) {
//                response.sendRedirect( "/index.html");
//            }
//            //Если нет то на страницу входа.
//        } else response.sendRedirect( "/index.html");
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
