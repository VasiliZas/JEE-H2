package vasilizas.myservice.security;

import static vasilizas.myservice.person.MyService.log;

public class PersonSecurity {

    private PersonSecurity() {
        // blank default constructor for utility class
    }

    public static void addLogin(String personType, String personName, String login, String password) {
        switch (personType) {
            case "Student" -> StudentSecurity.addLogin(personName, login, password);
            case "Teacher" -> TeacherSecurity.addLogin(personName, login, password);
            default -> log.warn("Incorrect variable entered");
        }
    }

    public static void addPassword(String personType, String personName, String password) {
        switch (personType) {
            case "Student" -> StudentSecurity.addPassword(personName, password);
            case "Teacher" -> TeacherSecurity.addPassword(personName, password);
            default -> log.warn("Incorrect variable entered");
        }
    }
}
