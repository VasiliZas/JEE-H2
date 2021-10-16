package web.vasilizas.controller.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.security.AdminSecurity.adminSecurity;
import static vasilizas.myservice.security.StudentSecurity.studentSecurity;
import static vasilizas.myservice.security.TeacherSecurity.teacherSecurity;
import static web.vasilizas.UrlRepository.urlMap;

@WebServlet("/auth")
public class Authentication extends HttpServlet {

    public static final Logger myLogger = LoggerFactory.getLogger("webLogger");
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String TYPE = "type";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(NAME);
        String type = request.getParameter(TYPE);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        myLogger.info("Passing authorization");
        if (type.equals("Student") && studentSecurity.check(name, login, password)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Admin") && adminSecurity.check(name, login, password)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Teacher") && teacherSecurity.check(name, login, password)) {
            setAttribute(session, type, login, name, response);
        } else {
            setAttribute(session, "Error", LOGIN, NAME, response);
        }
    }

    private void setAttribute(HttpSession session, String type, String login, String name, HttpServletResponse response) throws IOException {
        myLogger.info("Setting attributes during authorization");
        session.removeAttribute("password");
        session.setAttribute(TYPE, type);
        session.setAttribute(LOGIN, login);
        session.setAttribute(NAME, name);
        response.sendRedirect(urlMap.get(type));
    }
}
