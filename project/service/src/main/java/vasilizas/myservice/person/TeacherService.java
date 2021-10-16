package vasilizas.myservice.person;

import vasilizas.bean.Teacher;
import vasilizas.exception.MyWebAppException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static vasilizas.myservice.person.MyService.log;
import static vasilizas.myservice.security.PersonSecurity.personSecurity;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherService {

    public static final TeacherService teacherService = new TeacherService();
    private static Teacher teacher;

    private TeacherService() {
        // blank default constructor for utility class
    }

    private static Teacher getTeacher(String name) {
        for (Teacher t : teacherList) {
            if (t.getName().equals(name)) {
                teacher = t;
            } else throw new MyWebAppException("Incorrect data may have been entered. There is no such coincidence.");
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

    public void getTeacherSalaryInfo(String name) {
        teacherList.stream()
                .filter(t -> t.getName().equals(name))
                .forEach(t -> log.info("Teacher {}, salary {} ", name, t.getSalary()));
    }

    public void getTeacherInfo(String name) {
        teacherList.stream()
                .filter(t -> t.getName().equals(name))
                .forEach(t -> log.info("{} ", t));
    }

    public void setTeacherSalary(String name, int id, double salary) {
        if (personSecurity.checkTeacher(teacherList, id, name)) {
            teacherList.stream()
                    .filter(t -> t.getId() == id)
                    .filter(t -> t.getName().equals(name))
                    .map(Teacher::getSalary)
                    .forEach(s -> s.add(valueOf(salary)));
        } else {
            throw new MyWebAppException("Incorrect data may have been entered. There is no such coincidence.");
        }
    }

    public double averageSalary(String name, int finish) throws NullPointerException {
        var teachers = getTeacher(name);
        var list = teachers.getSalary();
        if (list == null) {
            list = new ArrayList<>();
        }
        return getAverage(list, finish);
    }
}
