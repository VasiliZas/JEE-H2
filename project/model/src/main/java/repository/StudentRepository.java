package repository;

import bean.Student;

import java.util.ArrayList;
import java.util.List;

public final class StudentRepository {

    public static List<Student> studentList = new ArrayList<>();

    private StudentRepository() {
    }
}
