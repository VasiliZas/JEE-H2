package web.vasilizas.controller.person;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
import static vasilizas.myservice.person.MyService.createAndAddPerson;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/add-student")
public class StudentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        session.setAttribute("add", "You add new student " + name + " with age " + age + " and login " + login);
        try {
            createAndAddPerson("Student", name, parseInt(age), login, password);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/addpersonpar");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
        }
    }
}
