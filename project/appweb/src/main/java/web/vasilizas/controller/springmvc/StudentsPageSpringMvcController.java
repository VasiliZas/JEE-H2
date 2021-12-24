package web.vasilizas.controller.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.vasilizas.myannotation.MyAopExceptionAnnotation;

import javax.servlet.http.HttpSession;

import static vasilizas.repository.StudentDbRepository.studentDbList;
import static web.vasilizas.repositories.strategy.StudentRepositoryStrategy.getStrategyInstance;

@Controller
@RequestMapping("/students")
public class StudentsPageSpringMvcController {

    @MyAopExceptionAnnotation
    @GetMapping("/student")
    public ModelAndView studentsPage(HttpSession session) {
        var model = new ModelAndView();
        String type = (String) session.getAttribute("type");
        if (type.equals("Student")) {
            model.addObject("marks", getStrategyInstance().getStudentMarks(studentDbList.get(0)));
            model.addObject("Group", studentDbList.get(0).getGroups());
            model.setViewName("student");
        } else {
            model.setViewName("error");
        }
        return model;
    }
}
