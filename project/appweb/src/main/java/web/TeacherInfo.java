package web;

import bean.Teacher;
import repository.TeacherRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static service.facade.Service.createAndAddPerson;
import static service.facade.TeacherService.setTeacherSalary;

@WebServlet(value = "/teacher-info")
public class TeacherInfo extends HttpServlet {
    private List<Teacher> teacherList;

    @Override
    public void init() throws ServletException {
        super.init();
        teacherList = TeacherRepository.teacherList;
        createAndAddPerson("Teacher", "Jasmin", 22);
        createAndAddPerson("Teacher", "Zlata", 25);
        setTeacherSalary("Zlata", 6000);
        setTeacherSalary("Jasmin", 2000);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<p><span style='color: blac;'>Teacher  information: "
                + "</span></p>");
        teacherList.forEach(teacher -> writer.write(teacher.toString() + "</p>"));
    }
}
