package web.vasilizas.controller.dataBase;

public class StudentDbService {

    private StudentDbService() {
        // blank default constructor for utility class
    }

    public static StudentDbService getInstance() {
        return SingletonHelper.instance;
    }

    public void addStudentMarks(String name, String theme, int mark, int id) {
//wip

    }

    public void removeStudentMarks(String name, String theme, int id) {
//wip
    }

    private static class SingletonHelper {
        private static final StudentDbService instance = new StudentDbService();
    }
}
