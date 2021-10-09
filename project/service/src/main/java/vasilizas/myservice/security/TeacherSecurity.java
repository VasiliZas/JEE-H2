package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.StudentRepository.studentList;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherSecurity extends AbstractSecurity {

    private TeacherSecurity() {
        // blank default constructor for utility class
    }

    public static void addLogin(String personName, String login, String password) {
        teacherList.stream()
                .filter(t -> t.getName().equals(personName))
                .forEach(t -> t.setLogin(login));
    }

    public static void addPassword(String personName,  String password) {
        teacherList.stream()
                .filter(t -> t.getName().equals(personName))
                .forEach(t -> t.setPassword(password));
    }

    static void getPassword(String personName) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(personName))
               .forEach(s -> log.info("{}", s.getPassword()));
    }

    public static boolean check(String name, String login, String password) {
        boolean result = false;
        if (checkName(name) && (checkLogin(name, login))) {
            result = checkPassword(name, login, password);
        }
        return result;
    }

    private static boolean checkLogin(String name, String login) {
        return teacherList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLogin)
                .anyMatch(s -> s.equals(login));
    }

    private static boolean checkName(String name) {
        return teacherList.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private static boolean checkPassword(String name, String login, String password) {
        return teacherList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getPassword)
                .allMatch(s -> s.equals(password));
    }
}
