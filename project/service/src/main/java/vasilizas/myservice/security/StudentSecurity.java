package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.StudentRepository.studentList;

public class StudentSecurity {

    public static final StudentSecurity studentSecurity = new StudentSecurity();

    private StudentSecurity() {
        // blank default constructor for utility class
    }

    private static boolean checkLogin(String name, String login) {
        return studentList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLogin)
                .anyMatch(s -> s.equals(login));
    }

    private static boolean checkName(String name) {
        return studentList.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private static boolean checkPassword(String name, String password) {
        return studentList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getPassword)
                .allMatch(s -> s.equals(password));
    }

    public void getPassword(String personName) {
        studentList.stream()
                .filter(student -> student.getName().equals(personName))
                .map(Person::getPassword)
                .forEach(s -> log.info("{}", s));
    }

    public void addLogin(String personName, String login) {
        studentList.stream()
                .filter(student -> student.getName().equals(personName))
                .forEach(student -> student.setLogin(login));
    }

    public void addPassword(String personName, String password) {
        studentList.stream()
                .filter(student -> student.getName().equals(personName))
                .forEach(student -> student.setPassword(password));
    }

    public boolean check(String name, String login, String password) {
        boolean result = false;
        if (checkName(name) && checkLogin(name, login)) {
            result = checkPassword(name, password);
        }
        return result;
    }
}
