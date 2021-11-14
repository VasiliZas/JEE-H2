package vasilizas.myservice.security;

import vasilizas.bean.db.MyAbstractEntity;
import vasilizas.bean.memory.Person;
import vasilizas.myservice.interfece.PersonCheckable;

import java.util.List;

public class CheckPerson implements PersonCheckable {

    private CheckPerson() {
        // blank default constructor for utility class
    }

    public static CheckPerson getInstance() {
        return CheckPerson.SingletonHelper.instance;
    }

    public boolean checkPerson(List<? extends Person> list, int id, String name) {
        return list.stream().anyMatch(s -> s.getId() == id)
                && list.stream()
                .filter(s -> s.getId() == id)
                .anyMatch(s -> s.getName().equals(name));
    }

    @Override
    public boolean checkPersonDb(List<? extends MyAbstractEntity> list, int id) {
        return list.stream().anyMatch(s -> s.getId() == id);
    }

    private static class SingletonHelper {
        private static final CheckPerson instance = new CheckPerson();
    }
}
