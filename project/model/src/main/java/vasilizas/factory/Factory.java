package vasilizas.factory;

import vasilizas.bean.*;

public final class Factory {

    private Factory() {
    }

    public static Student createStudent(String name, int age) {
        return new Student(name, age);
    }

    public static Teacher createTeacher(String name, int age) {
        return new Teacher(name, age);
    }

    public static Admin createAdmin() {
        return new Admin();
    }

    public static Person getTypePerson(String type, String name, int age) {
        return switch (type) {
            case "Student" -> new Student(name, age);
            case "Teacher" -> new Teacher(name, age);
            case "Admin" -> new Admin();
            default -> new DefaultPerson();
        };
    }
}

