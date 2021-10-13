package web.vasilizas.controller.person;

import vasilizas.myservice.person.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/averagesalary")
public class TeacherSalary extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");

        HttpSession session = req.getSession();
        var average = TeacherService.averageSalary();
        session.setAttribute("avgSalary", "Average salary of all teachers " + average + " eur");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/avg-salary");
        requestDispatcher.forward(req, resp);
    }
}
