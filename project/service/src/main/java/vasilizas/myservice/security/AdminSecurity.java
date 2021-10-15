package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.repository.AdminRepository.adminList;

public class AdminSecurity {
    private AdminSecurity() {
        // blank default constructor for utility class
    }

    public static boolean check(String name, String login, String password) {
        boolean result = false;
        if (checkName(name) && (checkLogin(name, login))) {
            result = checkPassword(name, password);
        }
        return result;
    }

    private static boolean checkLogin(String name, String login) {
        return adminList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLogin)
                .anyMatch(s -> s.equals(login));
    }

    private static boolean checkName(String name) {
        return adminList.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private static boolean checkPassword(String name, String password) {
        return adminList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getPassword)
                .allMatch(s -> s.equals(password));
    }
}
