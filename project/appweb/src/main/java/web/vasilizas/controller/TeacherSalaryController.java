package web.vasilizas.controller;

import vasilizas.myservice.person.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Double.valueOf;

@WebServlet("/teacher-salary")
public class TeacherSalaryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String login = req.getParameter("login");


        TeacherService.setTeacherSalary(name, login, valueOf(salary));
        HttpSession session = req.getSession();

        session.setAttribute("newteacher", name);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
        requestDispatcher.forward(req, resp);
    }
}
