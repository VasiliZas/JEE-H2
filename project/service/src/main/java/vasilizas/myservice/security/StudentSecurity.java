package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.myservice.person.MyService.log;
import static vasilizas.repository.StudentRepository.studentList;

public class StudentSecurity implements Security {

    private StudentSecurity() {
    }

    static void getPassword(String personName, String login) {
        studentList.stream()
                .filter(student -> student.getName().equals(personName))
                .map(Person::getLoginAndPassword)
                .map(stringStringMap -> stringStringMap.get(login))
                .forEach(s -> log.info("{}", s));
    }

    public static void security(String personName, String login, String password) {
        studentList.stream()
                .filter(student -> student.getName().equals(personName))
                .map(Person::getLoginAndPassword)
                .forEach(stringStringMap -> stringStringMap.put(login, password));
    }

    public static boolean check(String name, String login, String password) {
        boolean result = false;
        if (studentList.stream().anyMatch(student -> student.getName().equals(name))) {
            var list = studentList.stream()
                    .filter(a -> a.getName().equals(name))
                    .map(Person::getLoginAndPassword)
                    .map(stringStringMap -> stringStringMap.get(login))
                    .toList();

            result = list.get(0).equals(password);
        }
        return result;
    }
}
