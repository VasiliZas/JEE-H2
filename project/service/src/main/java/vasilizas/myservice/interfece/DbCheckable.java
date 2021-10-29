package vasilizas.myservice.interfece;

import vasilizas.bean.db.AbstractEntity;

import java.util.List;

public interface DbCheckable {
    boolean checkLogin(String name, String login, List<? extends AbstractEntity> list);

    boolean checkName(String name, List<? extends AbstractEntity> list);

    boolean checkPassword(String name, String password, List<? extends AbstractEntity> list);
}
