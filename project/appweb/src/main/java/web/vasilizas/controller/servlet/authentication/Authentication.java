package web.vasilizas.controller.servlet.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.myservice.security.PersonAuthentication;
import web.vasilizas.controller.servlet.database.DataBase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;
import static vasilizas.repository.AdminRepository.adminList;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;
import static web.vasilizas.UrlRepository.urlMap;

//@WebServlet("/auth")
public class Authentication extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private final Logger myLogger = LoggerFactory.getLogger(Authentication.class);
    private final PersonAuthentication personAuthentication = PersonAuthentication.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            myLogger.error(String.valueOf(e));
        }

        String name = request.getParameter(NAME);
        String type = request.getParameter(TYPE);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        myLogger.info("Passing authorization");
        if (type.equals("Student") && personAuthentication.checkStudentDb(name, login, password, getPersonFromDbInMemory(type, name, login))) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Admin") && personAuthentication.check(name, login, password, adminList)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Teacher") && personAuthentication.checkTeacherDb(name, login, password, getPersonFromDbInMemory(type, name, login))) {
            session.setAttribute("groups", teacherDbList);
            session.setAttribute("yourGroup", teacherDbList.get(0).getGroup());
            session.setAttribute("yourStudent", teacherDbList.get(0).getGroup().getStudents());
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

    private List getPersonFromDbInMemory(String type, String name, String login) {
        List list = new ArrayList();
        if (type.equals("Student")) {
            list = DataBase.getInstance().getStudentFromDb(name, login);
        }
        if (type.equals("Teacher")) {
            list = DataBase.getInstance().getTeacherFromDb(name, login);
        }
        return list;
    }
}
