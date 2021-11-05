package vasilizas.myservice.security;

import vasilizas.bean.memory.Person;
import vasilizas.myservice.interfece.Checkable;

import java.util.List;

public class PersonSecurityCheck implements Checkable {

    private PersonSecurityCheck() {
        // blank default constructor for utility class
    }

    public static PersonSecurityCheck getInstance() {
        return PersonSecurityCheck.SingletonHelper.instance;
    }

    @Override
    public boolean checkLogin(String name, String login, List<? extends Person> list) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLogin)
                .anyMatch(s -> s.equals(login));
    }

    @Override
    public boolean checkName(String name, List<? extends Person> list) {
        return list.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    @Override
    public boolean checkPassword(String name, String password, List<? extends Person> list) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getPassword)
                .allMatch(s -> s.equals(password));
    }

    private static class SingletonHelper {
        private static final PersonSecurityCheck instance = new PersonSecurityCheck();
    }
}
