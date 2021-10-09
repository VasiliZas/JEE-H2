package vasilizas.factory;

import vasilizas.bean.*;

public final class Factory {

    private Factory() {
        // blank default constructor for utility class
    }

    public static Student createStudent(String name, int age, String login, String passsword) {
        return new Student(name, age, login, passsword);
    }

    public static Teacher createTeacher(String name, int age, String login, String passsword) {
        return new Teacher(name, age, login, passsword);
    }

    public static Admin createAdmin() {
        return new Admin();
    }

    public static Person getTypePerson(String type, String name, int age, String login, String passsword) {
        return switch (type) {
            case "Student" -> new Student(name, age, login, passsword);
            case "Teacher" -> new Teacher(name, age, login, passsword);
            case "Admin" -> new Admin();
            default -> new DefaultPerson();
        };
    }
}

