package web.vasilizas.controller.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vasilizas.myservice.security.PersonAuthentication;
import web.vasilizas.controller.servlet.database.DataBase;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static vasilizas.repository.AdminRepository.adminList;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;

@Controller
public class AuthSpringMvcController {

    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String TYPE = "type";

    @PostMapping("/firstauth")
    public String firstAuthPage(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "type", required = false) String type,
                                @RequestParam(value = "login", required = false) String login,
                                @RequestParam(value = "password", required = false) String password,
                                @Autowired PersonAuthentication personAuthentication,
                                HttpSession session) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        if (type.equals("Student") && personAuthentication.checkStudentDb(name, login, password, getPersonFromDbInMemory(type, name, login))) {
            setAttribute(session, type, login, name);
            return "redirect:/students/student";
        }
        if (type.equals("Admin") && personAuthentication.check(name, login, password, adminList)) {
            setAttribute(session, type, login, name);
            return "redirect:/admins/admin";
        }
        if (type.equals("Teacher") && personAuthentication.checkTeacherDb(name, login, password, getPersonFromDbInMemory(type, name, login))) {
            session.setAttribute("groups", teacherDbList);
            session.setAttribute("yourGroup", teacherDbList.get(0).getGroup());
            session.setAttribute("yourStudent", teacherDbList.get(0).getGroup().getStudents());
            setAttribute(session, type, login, name);
            return "redirect:/teachers/teacher";
        } else {
            setAttribute(session, "Error", LOGIN, NAME);
            return "auth";
        }
    }

    @GetMapping("/out")
    public String userOut(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    private void setAttribute(HttpSession session, String type, String login, String name) {
        session.removeAttribute("password");
        session.setAttribute(TYPE, type);
        session.setAttribute(LOGIN, login);
        session.setAttribute(NAME, name);
    }

    private List getPersonFromDbInMemory(String type, String name, String login) {
        List list = new ArrayList();
        if (type.equals("Student")) {
            list = DataBase.getInstance().getStudentFromDb(name, login);
        }
        if (type.equals("Teacher")) {
            list = DataBase.getInstance().getTeacherFromDb(name, login);
        }
        return list;
    }
}
