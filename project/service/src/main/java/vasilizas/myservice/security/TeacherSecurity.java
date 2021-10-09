package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherSecurity extends AbstractSecurity {

    private TeacherSecurity() {
    }

    public static void addLoginAndPassword(String personName, String login, String password) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(personName))
                .map(Person::getLoginAndPassword)
                .forEach(stringStringMap -> stringStringMap.put(login, password));
    }

    static void getPassword(String personName, String login) {
        teacherList.stream()
                .filter(teacher -> teacher.getName().equals(personName))
                .map(Person::getLoginAndPassword)
                .map(stringStringMap -> stringStringMap.get(login))
                .forEach(s -> log.info("{}", s));
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
                .map(Person::getLoginAndPassword)
                .anyMatch(stringStringMap -> stringStringMap.containsKey(login));
    }

    private static boolean checkName(String name) {
        return teacherList.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private static boolean checkPassword(String name, String login, String password) {
        var list = teacherList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .map(stringStringMap -> stringStringMap.get(login))
                .toList();
        return list.get(0).equals(password);
    }
}
