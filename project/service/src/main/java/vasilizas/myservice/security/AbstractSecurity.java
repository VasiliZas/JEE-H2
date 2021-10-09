package vasilizas.myservice.security;

import vasilizas.bean.Person;

import java.util.List;


public abstract class AbstractSecurity<T extends Person> {

    public  void addLoginAndPassword(List<T> list, String name, String login, String password) {
        list.stream()
                .filter(admin -> admin.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .forEach(stringStringMap -> stringStringMap.put(login, password));
    }

    public  boolean check(List<T> list, String name, String login, String password) {
        boolean result = false;
        if (checkName(list,name)) {
            if (checkLogin(list,name, login)) {
                result = checkPassword(list,name, login, password);
            }
        }
        return result;
    }

    private boolean checkLogin(List<T> list, String name, String login) {
        return list.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .anyMatch(stringStringMap -> stringStringMap.containsKey(login));
    }

    private  boolean checkName(List<T> list, String name) {
        return list.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    private boolean checkPassword(List<T> list, String name, String login, String password) {
        boolean result = false;
        var list2 = list.stream()
                .filter(a -> a.getName().equals(name))
                .map(Person::getLoginAndPassword)
                .map(stringStringMap -> stringStringMap.get(login))
                .toList();
        return result = list2.get(0).equals(password);
    }
}
