package service.facade;

import static java.math.BigDecimal.valueOf;
import static repository.TeacherRepository.teacherList;
import static service.facade.Service.log;

public class TeacherService {
    private TeacherService() {
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
