package vasilizas.myservice.security;

import vasilizas.bean.Person;

import java.util.List;

public interface  Security<T extends Person> {

      static void addLoginAndPassword(String personName, String login, String password){}

      static void getPassword(String personName, String login){}

      default boolean check(List<T> list, String name, String login, String password) {
            return false;
      }
}
