package vasilizas.myservice.interfece;

import vasilizas.bean.Person;
import vasilizas.bean.db.MyAbstractEntity;

import java.util.List;

public interface PersonCheckable {
    boolean checkPerson(List<? extends Person> list, int id, String name);

    boolean checkPersonDb(List<? extends MyAbstractEntity> list, int id);
}
