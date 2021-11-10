package vasilizas.myservice.interfece;

import vasilizas.bean.Person;

import java.util.List;

public interface PersonCheckable {
    boolean checkPerson(List<? extends Person> list, int id, String name);
}
