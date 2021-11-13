package web.vasilizas.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.exception.MyWebAppException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.repository.StudentDbRepository.studentDbList;
import static web.vasilizas.repositories.factory.RepositoryFactory.getStudentRepository;

@WebServlet("/student")
public class StudentPage extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(StudentPage.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        myLogger.info("Arrive to Student page");
        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("type");
        try {
            if (type.equals("Student")) {
                req.setAttribute("marks", getStudentRepository("JPA").getStudentMarks(studentDbList.get(0)));
                req.setAttribute("Group", studentDbList.get(0).getGroups());
                myLogger.info("Go to Student work page");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student-page");
                requestDispatcher.forward(req, resp);
            } else {
                myLogger.info("redirect to home page");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home");
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            myLogger.warn(String.valueOf(e));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/error");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException | MyWebAppException ex) {
                myLogger.warn(String.valueOf(ex));
            }
        }
    }
}
