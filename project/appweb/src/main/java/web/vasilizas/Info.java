package web.vasilizas;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.person.MyService.createAndAddPerson;
import static vasilizas.myservice.person.TeacherService.setTeacherSalary;
import static vasilizas.repository.TeacherRepository.teacherList;
import static web.vasilizas.UrlRepository.urlMap;

@WebServlet("/info")
public class Info extends HttpServlet {
    private static final String INFO1 = "info";


    @Override
    public void init() throws ServletException {
        createAndAddPerson("Teacher", "Jasmin", 22);
        createAndAddPerson("Teacher", "Zlata", 25);
        setTeacherSalary("Zlata", 6000);
        setTeacherSalary("Jasmin", 2000);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String type = req.getParameter("kind");

        if (type.equals("Student")) {
            session.setAttribute(INFO1, type);
            resp.sendRedirect(urlMap.get("Error"));
            return;
        }
        if (type.equals("Teacher")) {
            session.setAttribute(INFO1, teacherList.get(0).toString());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/myweb/admin/info");
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(urlMap.get("Error"));
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        String type = req.getParameter("kind");
//
//        if (type.equals("Student")) {
//            session.setAttribute(INFO1, type);
//            resp.sendRedirect(urlMap.get("Error"));
//            return;
//        }
//        if (type.equals("Teacher")) {
//            session.setAttribute(INFO1, teacherList.get(0).toString());
//            resp.sendRedirect("/myweb/admin/info.jsp");
//        } else {
//            resp.sendRedirect(urlMap.get("Error"));
//        }
//    }
}
