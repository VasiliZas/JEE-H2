package vasilizas.myservice.interfece;

import vasilizas.bean.db.MyAbstractEntity;

import java.util.List;

public interface DbCheckable {
    boolean checkLogin(String name, String login, List<? extends MyAbstractEntity> list);

    boolean checkName(String name, List<? extends MyAbstractEntity> list);

    boolean checkPassword(String name, String password, List<? extends MyAbstractEntity> list);
}
