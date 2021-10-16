package vasilizas.myservice.security;

import vasilizas.bean.Student;
import vasilizas.bean.Teacher;

import java.util.List;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.myservice.security.StudentSecurity.studentSecurity;
import static vasilizas.myservice.security.TeacherSecurity.teacherSecurity;

public class PersonSecurity {

    public static final PersonSecurity personSecurity = new PersonSecurity();

    private PersonSecurity() {
        // blank default constructor for utility class
    }

    public void addLogin(String personType, String personName, String login) {
        switch (personType) {
            case "Student" -> studentSecurity.addLogin(personName, login);
            case "Teacher" -> teacherSecurity.addLogin(personName, login);
            default -> log.warn("Incorrect variable entered");
        }
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

