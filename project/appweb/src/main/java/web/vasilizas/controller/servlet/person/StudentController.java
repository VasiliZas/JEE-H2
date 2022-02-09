package web.vasilizas.controller.servlet.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.StudentDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
import static web.vasilizas.repositories.strategy.StudentRepositoryStrategy.getStrategyInstance;

@WebServlet("/add-student")
public class StudentController extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(StudentController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            HttpSession session = req.getSession();
            session.setAttribute("add", "You add new student " + name + " with age " + age + " and login " + login);
            getStrategyInstance().addPersonInDb(new StudentDb().withName(name)
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
            } catch (Exception ex) {
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
