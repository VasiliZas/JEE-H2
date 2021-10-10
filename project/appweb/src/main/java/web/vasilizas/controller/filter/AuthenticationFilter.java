package web.vasilizas.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.person.MyService.log;

@WebFilter(urlPatterns = "/myweb/admin/*")
public class AuthenticationFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

         boolean loggedLogin = session != null && session.getAttribute("type") != null;
        if (loggedLogin) {
            log.info("check type admin filter");
            String userRole = session.getAttribute("type").toString();
            if (userRole.equals("Admin")) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("/myweb/home");
                return;
            }
            //Если нет то на страницу входа.
        } else {
            response.sendRedirect("/index.jsp");
            return;
        }
    }

    @Override
    public void destroy() {
    }
}

