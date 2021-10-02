package repository;

import bean.Student;

import java.util.List;

import static factory.Factory.createStudent;

public final class StudentRepository {

    public static List<Student> studentList = List.of(
            createStudent("Bakke", 22),
            createStudent("Jasmin", 24),
            createStudent("Fox", 18)
    );

    private StudentRepository() {
    }
}
