package web.vasilizas.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/work")
public class WorkPageController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("type");

        switch (type) {
            case "Student" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student");
                requestDispatcher.forward(req, resp);
            }
            case "Teacher" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher");
                requestDispatcher.forward(req, resp);
            }
            case "Admin" -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
                requestDispatcher.forward(req, resp);
            }
            default -> {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/start");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
