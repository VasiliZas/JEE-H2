//package web.vasilizas.controller.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
////@WebFilter(filterName = "LoginFilter" , urlPatterns = "/*")//Все страницы сайта обрабатывает данный фильтр
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
//        //получение данных сессии
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpSession session = request.getSession();
//        //URL Запроса/переадресации на Servlet входа
//        String loginURI =  "/auth.html";
//        String attribute = (String) session.getAttribute("type");
//        //Если сессия ранее создана
//        boolean loggedIn = attribute!= null ;//|| attribute.equals("Student") || attribute.equals("Teacher") ;
//        boolean loginRequest = request.getRequestURI().equals(loginURI);
//        //Если запрос пришел со страницы с входом или сессия не пуста даем добро следовать дальше
//        //Если нет ридерект на страницу входа
//        if (loggedIn || loginRequest) {
//            chain.doFilter(request, response);
//        } else {
//            RequestDispatcher dispatcher = req.getRequestDispatcher(loginURI);
//            dispatcher.forward(req,res);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
