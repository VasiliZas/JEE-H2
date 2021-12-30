package web.vasilizas.controller.springmvc;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.myannotation.MyAopExceptionAnnotation;
import web.vasilizas.repositories.orm.SpringOrmTeacherRepository;
import web.vasilizas.repositories.strategy.StudentRepositoryStrategy;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.lang.Integer.parseInt;
import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminPageSpringMvcController {

    private final SpringOrmTeacherRepository teacherRepository;
    private final String addPerson = "addPerson";


    @GetMapping("/admin")
    public String adminsPage() {
        return "admin";
    }

    @GetMapping("/addperson")
    public String adminAddPersonPage() {
        return addPerson;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/addteacher")
    public String adminAddTeacher(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "age", required = false) String age,
                                  @RequestParam(value = "login", required = false) String login,
                                  @RequestParam(value = "password", required = false) String password,
                                  HttpSession session) {
        session.setAttribute("add", "You add new teacher " + name + " with age " + age + " and login " + login);
        getStrategyInstance().addPersonInDb(new TeacherDb()
                .withAge(parseInt(age))
                .withLogin(login)
                .withPassword(password)
                .withName(name));
        return addPerson;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/addstudent")
    public String adminAddStudent(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "age", required = false) String age,
                                  @RequestParam(value = "login", required = false) String login,
                                  @RequestParam(value = "password", required = false) String password,
                                  HttpSession session) {
        session.setAttribute("add", "You add new student " + name + " with age " + age + " and login " + login);
        StudentRepositoryStrategy.getStrategyInstance().addPersonInDb(new StudentDb().withName(name)
                .withAge(parseInt(age))
                .withLogin(login)
                .withPassword(password));
        return addPerson;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/teachersalary")
    public String adminAddTeacherSalary(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "id", required = false) String id,
                                        @RequestParam(value = "salary", required = false) String salary,
                                        HttpSession session) {
        var teacher = getStrategyInstance().find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
        getStrategyInstance().addTeachersSalary(teacher, Double.parseDouble(salary));
        session.setAttribute("add", "You add for teacher " + name + "  salary " + salary);
        return addPerson;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/averagesalary")
    public String adminGetAvgTeacherSalary(@RequestParam(value = "number", required = false) String number,
                                           @RequestParam(value = "id", required = false) String id,
                                           HttpSession session) {
        double average = getStrategyInstance().getAvgTeachersSalary(parseInt(id), parseInt(number));
        session.setAttribute("avgSalary", "Average salary teacher with id " + id + " is " + average + " eur");
        return "average";
    }

    @GetMapping("/avg-salary")
    public String adminGetAvgSalaryPersonPage() {
        return "average";
    }

    @MyAopExceptionAnnotation
    @GetMapping("/info2")
    public ModelAndView adminGetTeachersInfoPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<TeacherDb> list = teacherRepository.findAll();
        modelAndView.setViewName("info");
        modelAndView.addObject("teacherInfo", list);
        return modelAndView;
    }
}
