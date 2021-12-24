package web.vasilizas.controller.springmvc;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.myannotation.MyAopExceptionAnnotation;
import web.vasilizas.repositories.strategy.StudentRepositoryStrategy;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.lang.Integer.parseInt;
import static web.vasilizas.repositories.jpa.JpaTeacherRepository.getInstance;
import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

@Controller
@PropertySource("classpath:application.properties")
//@RequestMapping(path = "/admins", produces = "application/json;charset=UTF-8")
@RequestMapping("/admins")
//@ResponseBody
public class AdminPageSpringMvcController {

    private final String addPerson = "addPerson";

    @GetMapping("/admin")
    public String adminsPage() {
        return "admin";
    }

    @GetMapping("/addperson")
    public String adminAddPersonPage(Model model) {
        model.addAttribute("teacher", new TeacherDb());
        model.addAttribute("student", new StudentDb());

        return addPerson;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/addteacher")
    public ModelAndView adminAddTeacher(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "age", required = false) String age,
                                        @RequestParam(value = "login", required = false) String login,
                                        @RequestParam(value = "password", required = false) String password,
                                        HttpSession session) {
        var model = new ModelAndView();
        session.setAttribute("add", "You add new teacher " + name + " with age " + age + " and login " + login);
        getStrategyInstance().addPersonInDb(new TeacherDb()
                .withAge(parseInt(age))
                .withLogin(login)
                .withPassword(password)
                .withName(name));
        model.setViewName(addPerson);
        return model;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/addstudent")
    public ModelAndView adminAddStudent(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "age", required = false) String age,
                                        @RequestParam(value = "login", required = false) String login,
                                        @RequestParam(value = "password", required = false) String password,
                                        HttpSession session) {
        var model = new ModelAndView();
        session.setAttribute("add", "You add new student " + name + " with age " + age + " and login " + login);
        StudentRepositoryStrategy.getStrategyInstance().addPersonInDb(new StudentDb().withName(name)
                .withAge(parseInt(age))
                .withLogin(login)
                .withPassword(password));
        model.setViewName(addPerson);
        return model;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/teachersalary")
    public ModelAndView adminAddTeacherSalary(@RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "id", required = false) String id,
                                              @RequestParam(value = "salary", required = false) String salary,
                                              HttpSession session) {
        var model = new ModelAndView();
        var teacher = getStrategyInstance().find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
        getStrategyInstance().setTeacherRepository(getInstance()).addTeachersSalary(teacher, Double.parseDouble(salary));
        session.setAttribute("add", "You add for teacher " + name + "  salary " + salary);
        model.setViewName(addPerson);
        return model;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/averagesalary")
    public ModelAndView adminGetAvgTeacherSalary(@RequestParam(value = "number", required = false) String number,
                                                 @RequestParam(value = "id", required = false) String id,
                                                 HttpSession session) {
        double average = getStrategyInstance().getAvgTeachersSalary(parseInt(id), parseInt(number));
        session.setAttribute("avgSalary", "Average salary teacher with id " + id + " is " + average + " eur");
        var model = new ModelAndView();
        model.setViewName("average");
        return model;
    }

    @GetMapping("/avg-salary")
    public String adminGetAvgSalaryPersonPage() {
        return "average";
    }

    @MyAopExceptionAnnotation
    @GetMapping("/info2")
    public ModelAndView adminGetTeachersInfoPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<TeacherDb> list = getStrategyInstance().findAll();
        modelAndView.setViewName("info");
        modelAndView.addObject("teacherInfo", list);
        return modelAndView;
    }
}
