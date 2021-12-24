package web.vasilizas.controller.springmvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vasilizas.bean.db.TeacherDb;

import java.util.List;

import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

@Controller
//@RequestMapping(path = "/admins", produces = "application/json;charset=UTF-8")
@RequestMapping("/admins")
//@ResponseBody
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
    public ModelAndView adminGetTeachersInfoPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<TeacherDb> list = getStrategyInstance().findAll();
        modelAndView.setViewName("info");
        modelAndView.addObject("teacherInfo", list);
        return modelAndView;
    }
}
