package web.vasilizas.repositories.interfaces;

import vasilizas.bean.db.TeacherDb;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends Repository<TeacherDb> {

    void addPersonInDb(TeacherDb teacherDb);

    Optional<TeacherDb> find(int id);

    List<TeacherDb> findAll();

    void removeSalary(int id);

    void remove(int id);

    double getAvgTeachersSalary(int id, int number);

    void addTeachersSalary(TeacherDb teacherDb, double salary);
}
