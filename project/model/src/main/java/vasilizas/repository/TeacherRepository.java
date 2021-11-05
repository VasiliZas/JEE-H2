package vasilizas.repository;

import vasilizas.bean.memory.Teacher;

import java.util.ArrayList;
import java.util.List;

public final class TeacherRepository {

    public static List<Teacher> teacherList = new ArrayList<>();

    private TeacherRepository() {
        // blank default constructor for utility class
    }
}
