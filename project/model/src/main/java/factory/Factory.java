package factory;

import bean.Student;
import bean.Teacher;

public class Factory {

    private Factory() {
    }

    public static Student creatStudent(String name, int age) {
        return new Student(name, age);
    }

    public static Teacher creatTeacher(String name, int age) {
        return new Teacher(name, age);
    }

//    public static Person getTypePerson(String type) {
//        return switch (type) {
//            case "Student" -> new Student(name, age);
//            case "Teacher" -> new Teacher(name, age);
//            default -> new DefaultPerson();
//        };
//    }
}
