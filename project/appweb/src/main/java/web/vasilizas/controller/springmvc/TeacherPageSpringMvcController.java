package web.vasilizas.controller.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vasilizas.bean.db.Group;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;

import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;
import static web.vasilizas.repositories.strategy.StudentRepositoryStrategy.getStrategyInstance;

@Controller
@RequestMapping("/teachers")
public class TeacherPageSpringMvcController {

    private final String TEACHER = "teacher";

    @GetMapping("/teacher")
    public String studentsPage() {
        return TEACHER;
    }

    @PostMapping("/addmarks")
    public String addStudentsMarks(@RequestParam(value = "id", required = false) String id,
                                   @RequestParam(value = "theme", required = false) String theme,
                                   @RequestParam(value = "grade", required = false) String grade,
                                   HttpSession session) {
        String groupsName = workWithSession(id, theme, session);
        getStrategyInstance().addStudentMarks(theme, parseInt(grade), parseInt(id), groupsName);
        session.setAttribute("yourStudent", teacherDbList.get(0).getGroup().getStudents());
        return TEACHER;
    }

    @PostMapping("/removemarks")
    public String removeStudentsMarks(@RequestParam(value = "id", required = false) String id,
                                      @RequestParam(value = "theme", required = false) String theme,
                                      HttpSession session) {
        String groupsName = workWithSession(id, theme, session);
        getStrategyInstance().removeThemeMarks(Integer.parseInt(id), theme, groupsName);
        session.setAttribute("yourStudent", teacherDbList.get(0).getGroup().getStudents());
        return TEACHER;
    }

    private String workWithSession(String id, String theme, HttpSession session) {
        StudentDb user = getStrategyInstance().find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
        String name = user.getName();
        session.setAttribute("grade", "You remove grade for student " + name + " theme " + theme + " . ");
        var group = (Group) session.getAttribute("yourGroup");
        return group.getName();
    }
}
