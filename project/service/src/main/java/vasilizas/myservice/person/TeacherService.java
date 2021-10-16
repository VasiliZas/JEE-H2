package vasilizas.myservice.person;

import vasilizas.bean.Teacher;
import vasilizas.exception.MyWebAppException;
import vasilizas.myservice.security.PersonSecurity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherService {

    private static TeacherService instance;
    private static Teacher teacher;

    private TeacherService() {
        // blank default constructor for utility class
    }

    public static synchronized TeacherService getInstance() {
        if (instance == null) {
            instance = new TeacherService();
        }
        return instance;
    }

    private static void getTeacher(String name) {
        for (Teacher t : teacherList) {
            if (t != null && t.getName().equals(name)) {
                teacher = t;
            } else throw new MyWebAppException("Incorrect data may have been entered. There is no such coincidence.");
        }
    }

    private static double getAverage(List<BigDecimal> list, int finish) {
        int summ = 0;
        for (int i = 0; i < finish; i++) {
            summ = summ + list.get(i).intValueExact();
        }
        return (double) summ / finish;
    }

    public void setTeacherSalary(String name, int id, double salary) {
        if (PersonSecurity.getInstance().checkTeacher(teacherList, id, name)) {
            teacherList.stream()
                    .filter(t -> t.getId() == id)
                    .filter(t -> t.getName().equals(name))
                    .map(Teacher::getSalary)
                    .forEach(s -> s.add(valueOf(salary)));
        } else {
            throw new MyWebAppException("Incorrect data may have been entered. There is no such coincidence.");
        }
    }

    public double averageSalary(String name, int finish) {
        getTeacher(name);
        var list = teacher.getSalary();
        if (list == null) {
            list = new ArrayList<>();
        }
        return getAverage(list, finish);
    }
}
