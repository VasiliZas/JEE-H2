package web.vasilizas.controller.springmvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminPageSpringMvcController {

    @GetMapping("/admin")
    public String adminsPage() {
        return "admin";
    }

    @GetMapping("/addperson")
    public String adminAddPersonPage() {
        return "addPerson";
    }

    @GetMapping("/avg-salary")
    public String adminGetAvgSalaryPersonPage() {
        return "average";
    }

    @GetMapping("/info2")
    public String adminGetTeachersInfoPage() {
        return "redirect:/admins/info";
    }

    @GetMapping("/info")
    public String adminGetTeachersInfoViewPage() {
        return "info";
    }
}
