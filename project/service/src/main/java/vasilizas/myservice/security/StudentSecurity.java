package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.repository.StudentRepository.studentList;

public class StudentSecurity {

    private StudentSecurity() {
        // blank default constructor for utility class
    }

    public static StudentSecurity getInstance() {
        return StudentSecurity.SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final StudentSecurity instance = new StudentSecurity();
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

    public boolean check(String name, String login, String password) {
        boolean result = false;
        if (checkName(name) && checkLogin(name, login)) {
            result = checkPassword(name, password);
        }
        return result;
    }
}
