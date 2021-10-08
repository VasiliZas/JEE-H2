package web.vasilizas;

import vasilizas.bean.Teacher;
import vasilizas.repository.TeacherRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static vasilizas.myservice.person.MyService.createAndAddPerson;
import static vasilizas.myservice.person.TeacherService.setTeacherSalary;

@WebServlet("/teacherinf")
public class TeacherInfo extends HttpServlet {
    private List<Teacher> myteacherList;

    @Override
    public void init() throws ServletException {
        myteacherList = TeacherRepository.teacherList;
        createAndAddPerson("Teacher", "Jasmin", 22);
        createAndAddPerson("Teacher", "Zlata", 25);
        setTeacherSalary("Zlata", 6000);
        setTeacherSalary("Jasmin", 2000);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
super.service(req,resp);
        HttpSession session = req.getSession();
        session.setAttribute("teacher-info",myteacherList.get(1));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/teacher-info");
        requestDispatcher.forward(req, resp);
    }
}
