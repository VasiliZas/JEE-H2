package web.vasilizas.repositories.strategy;

import vasilizas.bean.db.TeacherDb;
import web.vasilizas.repositories.interfaces.TeacherRepository;

import java.util.List;
import java.util.Optional;

public class TeacherRepositoryStrategy {

    private static volatile TeacherRepositoryStrategy instance;
    private TeacherRepository teacherRepository;

    private TeacherRepositoryStrategy() {
        //singleton
    }

    public TeacherRepositoryStrategy(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public static TeacherRepositoryStrategy getStrategyInstance() {
        if (instance == null) {
            synchronized (TeacherRepositoryStrategy.class) {
                if (instance == null) {
                    instance = new TeacherRepositoryStrategy();
                }
            }
        }
        return instance;
    }

    public void addPersonInDb(TeacherDb teacherDb) {
        teacherRepository.addPersonInDb(teacherDb);
    }

    public Optional<TeacherDb> find(int id) {
        return teacherRepository.find(id);
    }

    public List<TeacherDb> findAll() {
        return teacherRepository.findAll();
    }

    public void removeSalary(int id) {
        teacherRepository.removeSalary(id);
    }

    public void remove(int id) {
        teacherRepository.remove(id);
    }

    public double getAvgTeachersSalary(int id, int number) {
        return teacherRepository.getAvgTeachersSalary(id, number);
    }

    public void addTeachersSalary(TeacherDb teacherDb, double salary) {
        teacherRepository.addTeachersSalary(teacherDb, salary);
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public TeacherRepositoryStrategy setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
        return this;
    }
}
