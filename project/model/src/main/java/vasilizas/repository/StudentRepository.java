package vasilizas.repository;

import vasilizas.bean.memory.Student;

import java.util.ArrayList;
import java.util.List;

public final class StudentRepository {

    public static List<Student> studentList = new ArrayList<>();

    private StudentRepository() {
        // blank default constructor for utility class
    }
}
