//package web.vasilizas.controller.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")//Все страницы сайта обрабатывает данный фильтр
//public class LoginFilter extends AbstractFilter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpSession session = request.getSession();
//
//        String loginURI = request.getContextPath() + "/auth.html";
//        String type = (String) session.getAttribute("type");
//
//        boolean loggedIn = type == null || !type.equals("Admin");
//        //boolean loginRequest = !request.getRequestURI().equals(loginURI);
//
//        if (loggedIn ) {
//            response.sendRedirect("/myweb/auth.html");
//return;
//        }
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
