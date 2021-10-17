package vasilizas.myservice.security;

import vasilizas.bean.Student;
import vasilizas.bean.Teacher;

import java.util.List;

public class PersonSecurity {

    private PersonSecurity() {
        // blank default constructor for utility class
    }

    public static PersonSecurity getInstance() {
        return PersonSecurity.SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final PersonSecurity instance = new PersonSecurity();
    }

    public boolean checkStudent(List<Student> list, int id, String name) {
        return list.stream().anyMatch(s -> s.getId() == id)
                && list.stream()
                .filter(s -> s.getId() == id)
                .anyMatch(s -> s.getName().equals(name));
    }

    public boolean checkTeacher(List<Teacher> list, int id, String name) {
        return list.stream().anyMatch(s -> s.getId() == id)
                && list.stream()
                .filter(s -> s.getId() == id)
                .anyMatch(s -> s.getName().equals(name));
    }
}

