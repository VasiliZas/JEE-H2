package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.repository.AdminRepository.adminList;

public class AdminSecurity extends AbstractSecurity {
    private AdminSecurity() {
    }

    public static void addLoginAndPassword(String name, String login, String password) {
        adminList.stream()
                .filter(admin -> admin.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .forEach(stringStringMap -> stringStringMap.put(login, password));
    }

    public static boolean check(String name, String login, String password) {
        boolean result = false;
        if (checkName(name) && (checkLogin(name, login))) {
            result = checkPassword(name, login, password);
        }
        return result;
    }

    private static boolean checkLogin(String name, String login) {
        return adminList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .anyMatch(stringStringMap -> stringStringMap.containsKey(login));
    }

    private static boolean checkName(String name) {
        return adminList.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private static boolean checkPassword(String name, String login, String password) {
        var list = adminList.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .map(stringStringMap -> stringStringMap.get(login))
                .toList();
        return list.get(0).equals(password);
    }
}
