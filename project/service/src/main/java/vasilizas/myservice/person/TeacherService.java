package vasilizas.myservice.person;

import vasilizas.bean.Teacher;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.TeacherRepository.teacherList;

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

    public static void setTeacherSalary(String name, String login, double salary) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(name))
                .filter(teacher -> teacher.getLogin().equals(login))
                .forEach(teacher -> teacher.setSalary(valueOf(salary)));
    }

    public static int averageSalary() {
       return (int) teacherList.stream()
                .map(Teacher::getSalary)
                .mapToInt(BigDecimal::intValueExact)
                .summaryStatistics()
                .getAverage();
    }
}
