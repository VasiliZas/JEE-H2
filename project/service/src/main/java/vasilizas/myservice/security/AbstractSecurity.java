package vasilizas.myservice.security;

import vasilizas.bean.Person;

import java.util.List;


public abstract class AbstractSecurity<T extends Person> {

//    public static void addLogin(List<T> list, String name, String login) {
//        list.stream()
//                .filter(a -> a.getName().equals(name))
//                .forEach(a -> a.setLogin(login));
//    }
//
//    public boolean check(List<T> list, String name, String login, String password) {
//        boolean result = false;
//        if (checkName(list, name)) {
//            if (checkLogin(list, name, login)) {
//                result = checkPassword(list, name, login, password);
//            }
//        }
//        return result;
//    }
//
//    private boolean checkLogin(List<T> list, String name, String login) {
//        return list.stream()
//                .filter(a -> a.getName().equals(name))
//                .map(Person::getLogin)
//                .anyMatch(s -> s.equals(login));
//    }
//
//    private boolean checkName(List<T> list, String name) {
//        return list.stream()
//                .anyMatch(a -> a.getName().equals(name));
//    }
//
//    private boolean checkPassword(List<T> list, String name, String login, String password) {
//        boolean result = false;
//        return list.stream()
//                .filter(a -> a.getName().equals(name))
//                .map(Person::getPassword)
//                .allMatch(s -> s.equals(password));
//    }
}
