package web.vasilizas.repositories;

import vasilizas.bean.db.MyAbstractEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends MyAbstractEntity> {
    List<T> findAll();

    Optional<T> find(int id);

    void remove(int id);
}
