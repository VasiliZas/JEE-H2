package web.vasilizas.controller.person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.repository.StudentRepository.studentList;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/student")
public class StudentPage extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        myLogger.info("Arrive to Student page");
        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("type");
        String name = (String) session.getAttribute("name");
        String login = (String) session.getAttribute("login");

        if (type.equals("Student")) {
            studentList.stream()
                    .filter(student -> student.getName().equals(name))
                    .filter(student -> student.getLogin().equals(login))
                    .forEach(student -> session.setAttribute("marks", student.getMarks().toString()));
            myLogger.info("Go to Student work page");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student-page");
            requestDispatcher.forward(req, resp);
        } else {
            myLogger.info("redirect to home page");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home");
            requestDispatcher.forward(req, resp);
        }
    }
}
