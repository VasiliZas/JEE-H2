package web.vasilizas.controller.person;

import web.vasilizas.repositories.DbTeacherRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/teacher-salary")
public class TeacherSalaryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String id = req.getParameter("id");

        try {
            //getInstance().setTeacherSalary(name, Integer.parseInt(id), parseDouble(salary)); - need when work with memory
            DbTeacherRepository.getInstance().setTeachersSalary(Integer.parseInt(id), BigDecimal.valueOf(Double.parseDouble(salary)));
            HttpSession session = req.getSession();
            session.setAttribute("add", "You add for teacher " + name + "  salary " + salary);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/addpersonpar");
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
