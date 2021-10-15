package vasilizas.myservice.person;

import vasilizas.bean.Teacher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static vasilizas.factory.Factory.createTeacher;
import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherService {

    private static Teacher teacher;

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
                .map(Teacher::getSalary)
                .forEach(s -> s.add(valueOf(salary)));
    }

    private static Teacher getTeacher(String name) {

        for (Teacher t : teacherList) {
            if (t.getName().equals(name)) {
                teacher = t;
            } else teacher = createTeacher("Name", 100, "login", "password");
        }
        return teacher;
    }

    private static double getAverage(List<BigDecimal> list, int finish) {
        int summ = 0;
        for (int i = 0; i < finish; i++) {
            summ = summ + list.get(i).intValueExact();
        }
        return (double) summ / finish;
    }

    public static double averageSalary(String name, int finish) throws NullPointerException {
        var teacher = getTeacher(name);
        var list = teacher.getSalary();
        if (list == null) {
            list = new ArrayList<>();
        }
        return getAverage(list, finish);
    }
}
