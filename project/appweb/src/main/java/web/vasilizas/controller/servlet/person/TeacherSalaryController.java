package web.vasilizas.controller.servlet.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.exception.MyWebAppException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static web.vasilizas.repositories.jpa.JpaTeacherRepository.getInstance;
import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

@WebServlet("/teacher-salary")
public class TeacherSalaryController extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(TeacherSalaryController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String id = req.getParameter("id");

        try {
            var teacher = getStrategyInstance().find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
            getStrategyInstance().setTeacherRepository(getInstance()).addTeachersSalary(teacher, Double.parseDouble(salary));
            HttpSession session = req.getSession();
            session.setAttribute("add", "You add for teacher " + name + "  salary " + salary);
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
