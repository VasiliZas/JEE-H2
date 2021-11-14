package web.vasilizas.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.Group;
import vasilizas.bean.db.StudentDb;
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

import static vasilizas.repository.TeacherDbRepository.teacherDbList;

@WebServlet("/removemarks")
public class GradeRemoveController extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(GradeRemoveController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String theme = req.getParameter("theme");

        try {
            HttpSession session = req.getSession();
            StudentDb user = RepositoryFactory.getStudentRepository("JPA").find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
            String name = user.getName();
            Group groups = (Group) session.getAttribute("yourGroup");
            req.setAttribute("grade", "You remove grade for student " + name
                    + " theme " + theme + " . ");
            RepositoryFactory.getStudentRepository("JPA").removeThemeMarks(Integer.parseInt(id), theme, groups.getName());
            session.setAttribute("yourStudent", teacherDbList.get(0).getGroup().getStudents());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher/teacher");
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException | MyWebAppException | PersistenceException e) {
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
