package web.vasilizas.controller.person;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;

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
import static web.vasilizas.repositories.jpa.JpaTeacherRepository.getInstance;
import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

@WebServlet("/addteacher")
public class TeacherController extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(TeacherController.class);

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
            getStrategyInstance().setTeacherRepository(getInstance()).addPersonInDb(new TeacherDb()
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
