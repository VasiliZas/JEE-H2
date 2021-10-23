package vasilizas.repository;

import vasilizas.bean.db.TeacherDb;

import java.util.ArrayList;
import java.util.List;

public class TeacherDbRepository {

    public static List<TeacherDb> teacherDbList = new ArrayList<>();

    private TeacherDbRepository() {
        // blank default constructor for utility class
    }
}
