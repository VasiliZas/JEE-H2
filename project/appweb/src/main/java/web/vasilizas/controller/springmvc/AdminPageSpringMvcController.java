package web.vasilizas.controller.springmvc;


import lombok.RequiredArgsConstructor;
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
import web.vasilizas.repositories.orm.SpringOrmStudentRepository;
import web.vasilizas.repositories.orm.SpringOrmTeacherRepository;

import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminPageSpringMvcController {

    private static final String PERSON = "addPerson";
    private final SpringOrmTeacherRepository teacherRepository;
    private final SpringOrmStudentRepository studentRepository;

    @GetMapping("/admin")
    public String adminsPage() {
        return "admin";
    }

    @GetMapping("/addperson")
    public String adminAddPersonPage() {
        return PERSON;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/addteacher")
    public ModelAndView adminAddTeacher(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "age", required = false) String age,
                                        @RequestParam(value = "login", required = false) String login,
                                        @RequestParam(value = "password", required = false) String password) {
        teacherRepository.addPersonInDb(new TeacherDb()
                .withAge(parseInt(age))
                .withLogin(login)
                .withPassword(password)
                .withName(name));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("add", "You add new teacher " + name + " with age " + age + " and login " + login);
        modelAndView.setViewName(PERSON);
        return modelAndView;
    }

    @PostMapping("/addstudent")
    public ModelAndView adminAddStudent(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "age", required = false) String age,
                                        @RequestParam(value = "login", required = false) String login,
                                        @RequestParam(value = "password", required = false) String password) {
        studentRepository.addPersonInDb(new StudentDb().withName(name)
                .withAge(parseInt(age))
                .withLogin(login)
                .withPassword(password));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("add", "You add new student " + name + " with age " + age + " and login " + login);
        modelAndView.setViewName(PERSON);
        return modelAndView;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/teachersalary")
    public ModelAndView adminAddTeacherSalary(@RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "id", required = false) String id,
                                              @RequestParam(value = "salary", required = false) String salary) {
        ModelAndView modelAndView = new ModelAndView();
        var teacher = teacherRepository.find(Integer.parseInt(id)).orElseThrow(MyWebAppException::new);
        teacherRepository.addTeachersSalary(teacher, Double.parseDouble(salary));
        modelAndView.addObject("add", "You add for teacher " + name + "  salary " + salary);
        modelAndView.setViewName(PERSON);
        return modelAndView;
    }

    @MyAopExceptionAnnotation
    @PostMapping("/averagesalary")
    public ModelAndView adminGetAvgTeacherSalary(@RequestParam(value = "number", required = false) String number,
                                                 @RequestParam(value = "id", required = false) String id) {
        double average = teacherRepository.getAvgTeachersSalary(parseInt(id), parseInt(number));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("avgSalary", "Average salary teacher with id " + id + " is " + average + " eur");
        modelAndView.setViewName("average");
        return modelAndView;
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
