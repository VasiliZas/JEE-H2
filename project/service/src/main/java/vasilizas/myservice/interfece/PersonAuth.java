package vasilizas.myservice.interfece;

import vasilizas.bean.memory.Person;

import java.util.List;

public interface PersonAuth {
    boolean check(String name, String login, String password, List<? extends Person> list);

}
