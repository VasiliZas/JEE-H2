//package web.vasilizas.controller.authentication;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/myweb/*")//Все страницы сайта обрабатывает данный фильтр
//public class LoginFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        //получение данных сессии
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(false);
//        //URL Запроса/переадресации на Servlet входа
//        String loginURI = request.getContextPath() + "/auth.jsp";
//        //Если сессия ранее создана
//        boolean loggedIn = session != null && session.getAttribute("login") != null && session.getAttribute("userRole") != null;
//        boolean loginRequest = request.getRequestURI().equals(loginURI);
//        //Если запрос пришел со страницы с входом или сессия не пуста даем добро следовать дальше
//        //Если нет ридерект на страницу входа
//        if (loggedIn || loginRequest) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.sendRedirect(loginURI);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
