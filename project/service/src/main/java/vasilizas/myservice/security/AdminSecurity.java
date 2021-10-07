package vasilizas.myservice.security;

import vasilizas.bean.Person;

import static vasilizas.repository.AdminRepository.adminList;

public class AdminSecurity implements Security {
    private AdminSecurity() {
    }

    public static void security(String name, String login, String password) {
        adminList.stream()
                .filter(admin -> admin.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .forEach(stringStringMap -> stringStringMap.put(login, password));
    }

    public static boolean check(String name, String login, String password) {
        boolean result = false;
        if (adminList.stream()
                .anyMatch(admin -> admin.getName().equals(name))) {
            var list = adminList.stream()
                    .filter(a -> a.getName().equals(name))
                    .map(Person::getLoginAndPassword)
                    .map(stringStringMap -> stringStringMap.get(login))
                    .toList();
             result = list.get(0).equals(password);
        }
    return result; }
}
