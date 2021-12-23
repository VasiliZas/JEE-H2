package web.vasilizas.controller.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageSpringMvcController {

    @GetMapping("/home")
    public String homePage() {
        return "index";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/start")
    public String authPage() {
        return "auth";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/work")
    public String workPage(HttpSession session) {
        String type = (String) session.getAttribute("type");
        switch (type) {
            case "Student" -> {
                return "redirect:/student";
            }
            case "Teacher" -> {
                return "redirect:/teacher";
            }
            case "Admin" -> {
                return "redirect:/admin";
            }
            default -> {
                return "redirect:/home";
            }
        }
    }
}
