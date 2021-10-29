package vasilizas.myservice.security;

import vasilizas.bean.Person;
import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.myservice.interfece.PersonAuth;

import java.util.List;

public class PersonAuthentication implements PersonAuth {

    private PersonAuthentication() {
        // blank default constructor for utility class
    }

    public static PersonAuthentication getInstance() {
        return PersonAuthentication.SingletonHelper.instance;
    }

    @Override
    public boolean check(String name, String login, String password, List<? extends Person> list) {
        boolean result = false;
        if (PersonSecurityCheck.getInstance().checkName(name, list)
                && PersonSecurityCheck.getInstance().checkLogin(name, login, list)) {
            result = PersonSecurityCheck.getInstance().checkPassword(name, password, list);
        }
        return result;
    }

    public boolean checkStudentDb(String name, String login, String password, List<StudentDb> listDb) {
        return DbPersonSecurityCheck.getInstance().checkDbStudent(name, login, password, listDb);
    }

    public boolean checkTeacherDb(String name, String login, String password, List<TeacherDb> list) {
        return DbPersonSecurityCheck.getInstance().checkDbTeacher(name, login, password, list);
    }

    private static class SingletonHelper {
        private static final PersonAuthentication instance = new PersonAuthentication();
    }
}
