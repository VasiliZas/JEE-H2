package web.vasilizas.controller.person;

import vasilizas.myservice.person.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/averagesalary")
public class TeacherSalary extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        String name = req.getParameter("name");

        try {
            double average = TeacherService.averageSalary(name, parseInt(number));
            HttpSession session = req.getSession();
            session.setAttribute("avgSalary", "Average salary " + name + " teacher  is " + average + " eur");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/avg-salary");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
        }

    }
}
