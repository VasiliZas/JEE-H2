package vasilizas.myservice.person;

import static java.math.BigDecimal.valueOf;
import static vasilizas.repository.TeacherRepository.teacherList;
import static vasilizas.myservice.person.MyService.log;

public class TeacherService {
    private TeacherService() {
        // blank default constructor for utility class
    }

    public static void getTeacherSalaryInfo(String name) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(name))
                .forEach(teacher -> log.info("Teacher {}, salary {} ", name, teacher.getSalary()));
    }

    public static void getTeacherInfo(String name) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(name))
                .forEach(teacher -> log.info("{} ", teacher));
    }

    public static void setTeacherSalary(String name, double salary) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(name))
                .forEach(teacher -> teacher.setSalary(valueOf(salary)));
    }
}
