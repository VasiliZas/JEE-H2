package web.vasilizas.controller.person;


import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.factory.RepositoryFactory;

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

@WebServlet("/addteacher")
public class TeacherController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            HttpSession session = req.getSession();
            session.setAttribute("add", "You add new teacher " + name + " with age " + age + " and login " + login);
            //MyService.getInstance().createAndAddPerson("Teacher", name, parseInt(age), login, password); - need when you work in memory
            RepositoryFactory.getTeacherRepository("JPA").addPersonInDb(new TeacherDb()
                    .withAge(parseInt(age))
                    .withLogin(login)
                    .withPassword(password)
                    .withName(name));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/addpersonpar");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/error");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException | MyWebAppException | PersistenceException ex) {
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
