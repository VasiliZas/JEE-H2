package web.vasilizas.controller.servlet.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.Group;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;
import static web.vasilizas.repositories.strategy.StudentRepositoryStrategy.getStrategyInstance;

@WebServlet("/addmarks")
public class GradeStudentControlle extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(GradeStudentControlle.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        String theme = req.getParameter("theme");
        String grade = req.getParameter("grade");

        try {
            HttpSession session = req.getSession();
            StudentDb user = getStrategyInstance().find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
            String studentName = user.getName();
            Group groups = (Group) session.getAttribute("yourGroup");
            req.setAttribute("grade", "You add grade " + grade + "  for student " + studentName
                    + " theme " + theme + " . ");
            getStrategyInstance().addStudentMarks(theme, parseInt(grade), parseInt(id), groups.getName());
            session.setAttribute("yourStudent", teacherDbList.get(0).getGroup().getStudents());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher/teacher");
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
