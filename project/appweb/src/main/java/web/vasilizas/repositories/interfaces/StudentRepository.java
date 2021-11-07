package web.vasilizas.repositories.interfaces;

import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends Repository<StudentDb> {

    void addPersonInDb(StudentDb studentDb);

    Optional<StudentDb> find(int id);

    List<StudentDb> findAll();

    void removeMarks(int id);

    void remove(int id);

    void addStudentMarks(String theme, int mark, int id);

    List<Marks> getStudentMarks(StudentDb studentDb);
}
