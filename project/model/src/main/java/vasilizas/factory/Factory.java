package vasilizas.factory;

import vasilizas.bean.*;

public final class Factory {

    private Factory() {
        // blank default constructor for utility class
    }

    public static Student createStudent(String name, int age, String login, String password) {
        return new Student(name, age, login, password);
    }

    public static Teacher createTeacher(String name, int age, String login, String password) {
        return new Teacher(name, age, login, password);
    }

    public static Admin createAdmin() {
        return new Admin();
    }

    public static Person getTypePerson(String type, String name, int age, String login, String password) {
        return switch (type) {
            case "Student" -> new Student(name, age, login, password);
            case "Teacher" -> new Teacher(name, age, login, password);
            case "Admin" -> new Admin();
            default -> new DefaultPerson();
        };
    }
}

