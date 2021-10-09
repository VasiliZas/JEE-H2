package vasilizas.myservice.security;

import static vasilizas.myservice.person.MyService.log;

public class PersonSecurity {

    private PersonSecurity() {
    }

    public static void addLoginAndPassword(String personType, String personName, String login, String password) {
        switch (personType) {
            case "Student" -> StudentSecurity.addLoginAndPassword(personName, login, password);
            case "Teacher" -> TeacherSecurity.addLoginAndPassword(personName, login, password);
            default -> log.warn("Incorrect variable entered");
        }
    }
}
