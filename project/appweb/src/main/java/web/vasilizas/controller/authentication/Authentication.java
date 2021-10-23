package web.vasilizas.controller.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.myservice.security.PersonAuthentication;
import web.vasilizas.controller.DataBase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Class.forName;
import static vasilizas.repository.AdminRepository.adminList;
import static vasilizas.repository.StudentDbRepository.studentDbList;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;
import static web.vasilizas.UrlRepository.urlMap;

@WebServlet("/auth")
public class Authentication extends HttpServlet {

    public static final Logger myLogger = LoggerFactory.getLogger("webLogger");
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String TYPE = "type";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            myLogger.error(String.valueOf(e));
        }
        new DataBase().getPersonFromDb();

        myLogger.info("{} ", studentDbList.get(1));
        String name = request.getParameter(NAME);
        String type = request.getParameter(TYPE);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        myLogger.info("Passing authorization");
        if (type.equals("Student") && PersonAuthentication.getInstance().check(name, login, password, studentDbList)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Admin") && PersonAuthentication.getInstance().check(name, login, password, adminList)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Teacher") && PersonAuthentication.getInstance().check(name, login, password, teacherDbList)) {
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
