package web.vasilizas.repositories.memory;

import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import web.vasilizas.repositories.interfaces.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryStudentRepository implements StudentRepository {
    private final List<StudentDb> studentList = new ArrayList<>();

    private MemoryStudentRepository() {
    }

    public static MemoryStudentRepository getInstance() {
        return SingletonHelper.instance;
    }

    public void addStudentMarks(String theme, int mark, int id, String group) {
        studentList.stream()
                .filter(student -> student.getId() == id)
                .map(StudentDb::getGrade)
                .forEach(marks -> marks.add(new Marks().withGrade(mark).withStudentId(id).withGroup(group)));
    }

    @Override
    public void addPersonInDb(StudentDb studentDb) {
        studentList.add(studentDb);

    }

    @Override
    public Optional<StudentDb> find(int id) {
        StudentDb user = null;
        for (StudentDb student : studentList) {
            if (student.getId() == id) {
                user = student;
            }
        }
        return Optional.of(user);
    }

    @Override
    public List<StudentDb> findAll() {
        return studentList;
    }

    @Override
    public void removeMarks(int id) {
        for (StudentDb student : studentList) {
            if (student.getId() == id) {
                student.setGrade(new ArrayList<>());
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                studentList.remove(i);
            }
        }
    }

    public List<Marks> getStudentMarks(StudentDb studentDb) {
        return studentDb.getGrade();
    }

    @Override
    public void removeThemeMarks(int id, String theme, String groups) {
        for (int i = 0; i < studentList.size(); i++) {
            List<Marks> list = studentList.get(i).getGrade();
            if (list.get(i).getStuid() == id && list.get(i).getTheme().equals(theme) && list.get(i).getGroups().equals(groups)) {
                list.remove(i);
            }
        }
    }

    private static class SingletonHelper {
        private static final MemoryStudentRepository instance = new MemoryStudentRepository();
    }
}
