package web.vasilizas.controller.authentication;

import vasilizas.myservice.security.AdminSecurity;
import vasilizas.myservice.security.StudentSecurity;
import vasilizas.myservice.security.TeacherSecurity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/auth")
public class Authentication extends HttpServlet {

    @Override
    public void init() {
        AdminSecurity.security("Vasili", "mylogin", "456987");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if (type.equals("Student") && StudentSecurity.check(name, login, password)) {
            session.setAttribute("type", type);
            session.setAttribute("login", login);
            session.setAttribute("name", name);
            response.sendRedirect("/myweb/student/student.html");
            return;
        }
        if (type.equals("Admin") && AdminSecurity.check(name, login, password)) {
            session.setAttribute("type", type);
            session.setAttribute("login", login);
            session.setAttribute("name", name);
            response.sendRedirect("/myweb/admin/admin.html");
            return;
        }
        if (type.equals("Teacher") && TeacherSecurity.check(name, login, password)) {
            session.setAttribute("login", login);
            session.setAttribute("type", type);
            session.setAttribute("name", name);
            response.sendRedirect("/myweb/teacher/teacher.html");
            return;
        } else  {
            session.setAttribute("login", "null");
            session.setAttribute("type", "null");
            session.setAttribute("name", "null");
             response.sendRedirect("/myweb/error/auth-error.html");
             return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
}
