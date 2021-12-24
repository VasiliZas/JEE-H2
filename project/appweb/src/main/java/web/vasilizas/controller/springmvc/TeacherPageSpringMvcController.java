package web.vasilizas.controller.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachers")
public class TeacherPageSpringMvcController {

    @GetMapping("/teacher")
    public String studentsPage() {
        return "teacher";
    }

    @PostMapping("/addmarks")
    public String addStudentsMarks() {
        return "teacher";
    }

    @PostMapping("/removemarks")
    public String removeStudentsMarks() {
        return "teacher";
    }
}
