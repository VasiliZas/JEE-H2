package web.vasilizas.controller.springmvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vasilizas.bean.db.Group;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.myannotation.MyAopExceptionAnnotation;
import web.vasilizas.repositories.orm.SpringOrmStudentRepository;

import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherPageSpringMvcController {

    private static final String TEACHER = "teacher";
    private final SpringOrmStudentRepository studentRepository;

    @GetMapping("/teacher")
    public String teachersPage() {
        return TEACHER;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/addmarks")
    public ModelAndView addStudentsMarks(@RequestParam(value = "id", required = false) String id,
                                         @RequestParam(value = "theme", required = false) String theme,
                                         @RequestParam(value = "grade", required = false) String grade,
                                         HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        studentRepository.addStudentMarks(theme, parseInt(grade), parseInt(id), getGroupName(session));
        modelAndView.addObject("grade", "You add grade for student " + getUserName(id) + " theme " + theme + " . ");
        modelAndView.addObject("yourStudent", teacherDbList.get(0).getGroup().getStudents());
        return modelAndView;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/removemarks")
    public ModelAndView removeStudentsMarks(@RequestParam(value = "id", required = false) String id,
                                            @RequestParam(value = "theme", required = false) String theme,
                                            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        studentRepository.removeThemeMarks(Integer.parseInt(id), theme, getGroupName(session));
        modelAndView.addObject("yourStudent", teacherDbList.get(0).getGroup().getStudents());
        modelAndView.addObject("grade", "You remove grade for student " + getUserName(id) + " theme " + theme + " . ");
        modelAndView.setViewName(TEACHER);
        return modelAndView;
    }

    private String getGroupName(HttpSession session) {
        var group = (Group) session.getAttribute("yourGroup");
        return group.getName();
    }

    private String getUserName(String id) {
        StudentDb user = studentRepository.find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
        return user.getName();
    }
}
