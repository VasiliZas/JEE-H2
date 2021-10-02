package web;

import bean.Teacher;
import repository.TeacherRepository;
import service.facade.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/teacherInfo")
public class TeacherInfo extends HttpServlet {
    private TeacherService teacherService;
    private List<Teacher> teacherList;

    @Override
    public void init() throws ServletException {
        super.init();
        teacherService = new TeacherService();
        teacherList = TeacherRepository.teacherList;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<p><span style='color: blac;'>Teacher  information: "
                + "</span></p>");
        teacherList.forEach(teacher -> writer.write(teacher.toString() + "</p>"));
    }
}
