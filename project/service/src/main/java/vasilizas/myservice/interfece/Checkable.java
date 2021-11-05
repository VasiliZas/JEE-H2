package vasilizas.myservice.interfece;

import vasilizas.bean.memory.Person;

import java.util.List;

public interface Checkable {

    boolean checkLogin(String name, String login, List<? extends Person> list);

    boolean checkName(String name, List<? extends Person> list);

    boolean checkPassword(String name, String password, List<? extends Person> list);
}
