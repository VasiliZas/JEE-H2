package web.vasilizas.controller.person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static vasilizas.myservice.person.MyService.myService;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/add-student")
public class StudentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            HttpSession session = req.getSession();
            session.setAttribute("add", "You add new student " + name + " with age " + age + " and login " + login);
            myService.createAndAddPerson("Student", name, parseInt(age), login, password);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/addpersonpar");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/error");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
