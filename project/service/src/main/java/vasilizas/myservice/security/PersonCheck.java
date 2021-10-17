package vasilizas.myservice.security;

import vasilizas.bean.Person;
import vasilizas.myservice.interfece.PersonCheckable;

import java.util.List;

public class PersonCheck implements PersonCheckable {

    private PersonCheck() {
        // blank default constructor for utility class
    }

    public static PersonCheck getInstance() {
        return PersonCheck.SingletonHelper.instance;
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
        private static final PersonCheck instance = new PersonCheck();
    }
}
