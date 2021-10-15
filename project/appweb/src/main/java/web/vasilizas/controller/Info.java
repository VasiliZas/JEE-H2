package web.vasilizas.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.String.valueOf;
import static vasilizas.repository.TeacherRepository.teacherList;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/info2")
public class Info extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        myLogger.info("Info about teacher");
        HttpSession session = req.getSession();

        try {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student-page");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(valueOf(e));
        }
    }
}
