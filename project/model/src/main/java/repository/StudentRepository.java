package repository;

import bean.Student;

import java.util.List;

import static factory.Factory.creatStudent;

public class StudentRepository {

    public static List<Student> studentList = List.of(
            creatStudent("Bakke", 22),
            creatStudent("Jasmin", 24),
            creatStudent("Fox", 18)
    );

    private StudentRepository() {
    }
}
