package web.vasilizas.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/work")
public class WorkPageController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("type");

        switch (type) {
            case "Student" -> {
                myLogger.info("Forward to Student page");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student");
                requestDispatcher.forward(req, resp);
            }
            case "Teacher" -> {
                myLogger.info("Forward to Teacher page");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher/teacher");
                requestDispatcher.forward(req, resp);
            }
            case "Admin" -> {
                myLogger.info("Forward to Admin page");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/admin");
                requestDispatcher.forward(req, resp);
            }
            default -> {
                myLogger.info("Forward to Start page");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/start");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
