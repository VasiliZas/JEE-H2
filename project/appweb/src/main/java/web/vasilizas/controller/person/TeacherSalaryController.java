package web.vasilizas.controller.person;

import vasilizas.myservice.person.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.Double.parseDouble;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/teacher-salary")
public class TeacherSalaryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String login = req.getParameter("login");

        try {
            TeacherService.setTeacherSalary(name, login, parseDouble(salary));
            HttpSession session = req.getSession();
            session.setAttribute("add", "You add new teacher " + name + " with salary " + salary);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/addpersonpar");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
        }
    }
}
