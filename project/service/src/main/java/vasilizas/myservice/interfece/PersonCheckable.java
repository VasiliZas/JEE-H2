package vasilizas.myservice.interfece;

import vasilizas.bean.Person;
import vasilizas.bean.db.AbstractEntity;

import java.util.List;

public interface PersonCheckable {
    boolean checkPerson(List<? extends Person> list, int id, String name);

    boolean checkPersonDb(List<? extends AbstractEntity> list, int id);
}
