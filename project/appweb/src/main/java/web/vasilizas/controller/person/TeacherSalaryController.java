package web.vasilizas.controller.person;

import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.factory.RepositoryFactory;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/teacher-salary")
public class TeacherSalaryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String id = req.getParameter("id");

        try {
            RepositoryFactory.getTeacherRepository("JPA").find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
            var teacher = RepositoryFactory.getTeacherRepository("JPA").find(Integer.parseInt(id)).get();
            RepositoryFactory.getTeacherRepository("JPA").addTeachersSalary(teacher, Double.parseDouble(salary));

            HttpSession session = req.getSession();
            session.setAttribute("add", "You add for teacher " + name + "  salary " + salary);
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
