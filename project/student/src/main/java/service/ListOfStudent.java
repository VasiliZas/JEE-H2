package service;

import bean.Student;

import java.util.List;

public class ListOfStudent {
    public static final List<Student> studentList = List.of(
            new Student("Student1", 8),
            new Student("Student2", 2),
            new Student("Student3", 7),
            new Student("Student4", 9),
            new Student("Student5", 5),
            new Student("Student6", 4));


    private ListOfStudent() {
    }

}
