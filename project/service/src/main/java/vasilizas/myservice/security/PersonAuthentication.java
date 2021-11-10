package vasilizas.myservice.security;

import vasilizas.bean.Person;
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

    private static class SingletonHelper {
        private static final PersonAuthentication instance = new PersonAuthentication();
    }
}
