package web.vasilizas.repositories.strategy;

import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import web.vasilizas.repositories.interfaces.StudentRepository;

import java.util.List;
import java.util.Optional;

import static web.vasilizas.repositories.jpa.JpaStudentRepository.getInstance;

public class StudentRepositoryStrategy {

    private static volatile StudentRepositoryStrategy instance;

    private StudentRepository studentRepository;

    private StudentRepositoryStrategy() {
        //singleton
    }

    public static StudentRepositoryStrategy getStrategyInstance() {
        if (instance == null) {
            synchronized (StudentRepositoryStrategy.class) {
                if (instance == null) {
                    instance = new StudentRepositoryStrategy().setStudentRepository(getInstance());
                }
            }
        }
        return instance;
    }

    public void addPersonInDb(StudentDb studentDb) {
        studentRepository.addPersonInDb(studentDb);
    }

    public Optional<StudentDb> find(int id) {
        return studentRepository.find(id);
    }

    public List<StudentDb> findAll() {
        return studentRepository.findAll();
    }

    public void removeMarks(int id) {
        studentRepository.removeMarks(id);
    }

    public void remove(int id) {
        studentRepository.remove(id);
    }

    public void addStudentMarks(String theme, int mark, int id, String group) {
        studentRepository.addStudentMarks(theme, mark, id, group);
    }

    public List<Marks> getStudentMarks(StudentDb studentDb) {
        return studentRepository.getStudentMarks(studentDb);
    }

    public void removeThemeMarks(int id, String theme, String groups) {
        studentRepository.removeThemeMarks(id, theme, groups);
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public StudentRepositoryStrategy setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        return this;
    }
}
