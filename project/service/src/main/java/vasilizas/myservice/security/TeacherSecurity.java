package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.TeacherRepository.teacherList;

public class TeacherSecurity implements Security {

    private TeacherSecurity() {
    }

    public static void security(String personName, String login, String password) {
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
        if (teacherList.stream().anyMatch(teacher -> teacher.getName().equals(name))) {
            var list = teacherList.stream()
                    .filter(a -> a.getName().equals(name))
                    .map(Person::getLoginAndPassword)
                    .map(stringStringMap -> stringStringMap.get(login))
                    .toList();

            result = list.get(0).equals(password);
        }
        return result;
    }
}
