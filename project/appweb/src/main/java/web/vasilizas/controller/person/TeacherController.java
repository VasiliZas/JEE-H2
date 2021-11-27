package web.vasilizas.controller.person;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.TeacherDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
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
            getStrategyInstance().addPersonInDb(new TeacherDb()
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
            } catch (Exception ex) {
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
