package web.vasilizas.controller.person;

import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.jpa.DbStudentRepository;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Integer.parseInt;
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
            //MyService.getInstance().createAndAddPerson("Student", name, parseInt(age), login, password); - need when you work in memory
            DbStudentRepository.getInstance().addPersonInDb(new StudentDb().withName(name)
                    .withAge(parseInt(age))
                    .withLogin(login)
                    .withPassword(password));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/addpersonpar");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/error");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException | MyWebAppException | PersistenceException ex) {
                ex.printStackTrace();
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
