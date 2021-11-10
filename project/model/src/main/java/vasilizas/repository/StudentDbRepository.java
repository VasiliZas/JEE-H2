package vasilizas.repository;

import vasilizas.bean.db.StudentDb;

import java.util.ArrayList;
import java.util.List;

public class StudentDbRepository {

    public static List<StudentDb> studentDbList = new ArrayList<>();

    private StudentDbRepository() {
        // blank default constructor for utility class
    }
}
