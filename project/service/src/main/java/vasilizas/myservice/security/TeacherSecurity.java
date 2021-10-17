package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherSecurity {

    private TeacherSecurity() {
        // blank default constructor for utility class
    }

    public static TeacherSecurity getInstance() {
        return TeacherSecurity.SingletonHelper.instance;
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

    private static boolean checkPassword(String name, String password) {
        return teacherList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getPassword)
                .allMatch(s -> s.equals(password));
    }

    public boolean check(String name, String login, String password) {
        boolean result = false;
        if (checkName(name) && (checkLogin(name, login))) {
            result = checkPassword(name, password);
        }
        return result;
    }

    private static class SingletonHelper {
        private static final TeacherSecurity instance = new TeacherSecurity();
    }
}
