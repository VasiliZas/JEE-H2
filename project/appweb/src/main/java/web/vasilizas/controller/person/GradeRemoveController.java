package web.vasilizas.controller.person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.person.StudentService.removeStudentMarks;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/removemarks")
public class GradeRemoveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String theme = req.getParameter("theme");

        try {
            HttpSession session = req.getSession();
            session.setAttribute("grade", "You remove grade for student " + name
                    + " theme " + theme + " . ");
            removeStudentMarks(name, theme, Integer.parseInt(id));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher/teacher");
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