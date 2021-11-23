package web.vasilizas.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@WebServlet("/averagesalary")
public class AvgTeacherSalaryController extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(AvgTeacherSalaryController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        String id = req.getParameter("id");

        try {
            double average = getStrategyInstance().setTeacherRepository(getInstance()).getAvgTeachersSalary(parseInt(id), parseInt(number));
            HttpSession session = req.getSession();
            session.setAttribute("avgSalary", "Average salary teacher with id " + id + " is " + average + " eur");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/avg-salary");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/error");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException ex) {
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
