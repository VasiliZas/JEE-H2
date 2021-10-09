package web.vasilizas.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.factory.Factory.createTeacher;
import static vasilizas.repository.TeacherRepository.teacherList;

@WebServlet("/addteacher")
public class AddTeacher extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");

        HttpSession session = req.getSession();
        session.setAttribute("newteacher", name);

        teacherList.add(createTeacher(name, Integer.valueOf(age), "TLogin", "12345"));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/add-teacher");
        requestDispatcher.forward(req, resp);
    }
}
