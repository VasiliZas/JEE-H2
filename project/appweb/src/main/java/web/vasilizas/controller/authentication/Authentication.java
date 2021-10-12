package web.vasilizas.controller.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.myservice.security.AdminSecurity;
import vasilizas.myservice.security.StudentSecurity;
import vasilizas.myservice.security.TeacherSecurity;
import vasilizas.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.person.MyService.createAndAddPerson;
import static web.vasilizas.UrlRepository.urlMap;

public class Authentication extends HttpServlet {

    public static final Logger myLogger = LoggerFactory.getLogger("webLogger");


    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String TYPE = "type";

    @Override
    public void init() throws ServletException {
        createAndAddPerson("Student", "Bakke", 22, "myDog", "poi");
        StudentRepository.studentList.stream()
                        .filter(student -> student.getName().equals("Bakke"))
                        .map(student -> student.getMarks())
                        .forEach(integerList -> integerList.add(89));

        createAndAddPerson("Teacher", "Jasmin", 22, "myCat", "lkj");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(NAME);
        String type = request.getParameter(TYPE);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        myLogger.info("Passing authorization");
        if (type.equals("Student") && StudentSecurity.check(name, login, password)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Admin") && AdminSecurity.check(name, login, password)) {
            setAttribute(session, type, login, name, response);
            return;
        }
        if (type.equals("Teacher") && TeacherSecurity.check(name, login, password)) {
            setAttribute(session, type, login, name, response);
        } else {
            setAttribute(session, "Error", LOGIN, NAME, response);
        }
    }

    private void setAttribute(HttpSession session, String type, String login, String name, HttpServletResponse response) throws IOException {
        myLogger.info("Setting attributes during authorization");
        session.setAttribute(TYPE, type);
        session.setAttribute(LOGIN, login);
        session.setAttribute(NAME, name);
        response.sendRedirect(urlMap.get(type));
    }
}
