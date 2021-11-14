package vasilizas.myservice.security;

import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;

import java.util.List;

public class DbPersonSecurityCheck {

    private DbPersonSecurityCheck() {
        //singleton
    }

    public static DbPersonSecurityCheck getInstance() {
        return SingletonHelper.instance;
    }

    public boolean checkDbStudent(String name, String login, String password, List<StudentDb> listS) {
        return checkDbStudentName(name, listS) && checkDbStudentLogin(name, login, listS) && checkDbStudentPassword(name, password, listS);
    }

    public boolean checkDbTeacher(String name, String login, String password, List<TeacherDb> list) {
        return checkDbTeacherName(name, list) && checkDbTeacherLogin(name, login, list) && checkDbTeacherPassword(name, password, list);
    }

    private boolean checkDbStudentLogin(String name, String login, List<StudentDb> list) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(StudentDb::getLogin)
                .anyMatch(s -> s.equals(login));
    }

    private boolean checkDbTeacherName(String name, List<TeacherDb> list) {
        return list.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private boolean checkDbTeacherPassword(String name, String password, List<TeacherDb> list) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(TeacherDb::getPassword)
                .allMatch(s -> s.equals(password));
    }

    private boolean checkDbTeacherLogin(String name, String login, List<TeacherDb> list) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(TeacherDb::getLogin)
                .anyMatch(s -> s.equals(login));
    }

    private boolean checkDbStudentName(String name, List<StudentDb> list) {
        return list.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private boolean checkDbStudentPassword(String name, String password, List<StudentDb> list) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(StudentDb::getPassword)
                .allMatch(s -> s.equals(password));
    }

    private static class SingletonHelper {
        private static final DbPersonSecurityCheck instance = new DbPersonSecurityCheck();
    }
}
