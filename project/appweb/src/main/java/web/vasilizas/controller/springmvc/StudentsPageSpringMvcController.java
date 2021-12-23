package web.vasilizas.controller.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentsPageSpringMvcController {

    @GetMapping("/student")
    public String studentsPage() {
        return "student";
    }
}
