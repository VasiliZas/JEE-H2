package web.vasilizas.controller.springmvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.vasilizas.myannotation.MyAopExceptionAnnotation;
import web.vasilizas.repositories.orm.SpringOrmStudentRepository;

import javax.servlet.http.HttpSession;

import static vasilizas.repository.StudentDbRepository.studentDbList;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsPageSpringMvcController {

    private final SpringOrmStudentRepository studentRepository;

    @MyAopExceptionAnnotation
    @GetMapping("/student")
    public ModelAndView studentsPage(HttpSession session) {
        var model = new ModelAndView();
        String type = (String) session.getAttribute("type");
        if (type.equals("Student")) {
            model.addObject("marks", studentRepository.getStudentMarks(studentDbList.get(0)));
            model.addObject("Group", studentDbList.get(0).getGroups());
            model.setViewName("student");
        } else {
            model.setViewName("notauth");
        }
        return model;
    }
}
