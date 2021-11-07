package web.vasilizas.controller.person;

import web.vasilizas.repositories.factory.RepositoryFactory;

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

@WebServlet("/averagesalary")
public class AvgTeacherSalaryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        String id = req.getParameter("id");

        try {
            double average = RepositoryFactory.getTeacherRepository("JPA").getAvgTeachersSalary(parseInt(id), parseInt(number));
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
