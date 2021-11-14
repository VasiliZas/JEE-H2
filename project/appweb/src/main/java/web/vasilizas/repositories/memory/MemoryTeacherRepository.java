package web.vasilizas.repositories.memory;

import vasilizas.bean.db.Salary;
import vasilizas.bean.db.TeacherDb;
import vasilizas.myservice.person.TeacherService;
import web.vasilizas.repositories.interfaces.TeacherRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;

public class MemoryTeacherRepository implements TeacherRepository {
    private final List<TeacherDb> teacherDbs = new ArrayList<>();

    private MemoryTeacherRepository() {
    }

    public static MemoryTeacherRepository getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void addPersonInDb(TeacherDb teacherDb) {
        teacherDbs.add(teacherDb);

    }

    @Override
    public Optional<TeacherDb> find(int id) {
        TeacherDb user = null;
        for (TeacherDb teacherDb : teacherDbs) {
            if (teacherDb.getId() == id) {
                user = teacherDb;
            }
        }
        return Optional.of(user);
    }

    @Override
    public List<TeacherDb> findAll() {
        return teacherDbs;
    }

    @Override
    public void removeSalary(int id) {
        for (TeacherDb teacherDb : teacherDbs) {
            if (teacherDb.getId() == id) {
                teacherDb.setSalary(new ArrayList<>());
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < teacherDbs.size(); i++) {
            if (teacherDbs.get(i).getId() == id) {
                teacherDbs.remove(i);
            }
        }
    }

    @Override
    public double getAvgTeachersSalary(int id, int number) {
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < teacherDbs.size(); i++) {
            if (teacherDbs.get(i).getId() == id) {
                var salaryList = teacherDbs.get(i).getSalary();
                for (Salary sal : salaryList) {
                    list.add(sal.getSalary());
                }
            }
        }
        return TeacherService.getAverage(list, number);
    }

    @Override
    public void addTeachersSalary(TeacherDb teacherDb, double salary) {
        var id = teacherDb.getId();
        for (TeacherDb teacher : teacherDbs) {
            if (teacher.getId() == id) {
                teacher.getSalary().add(new Salary().withSalary(valueOf(salary)).withTid(teacherDb.getId()));
            }
        }
    }

    private static class SingletonHelper {
        private static final MemoryTeacherRepository instance = new MemoryTeacherRepository();
    }
}
