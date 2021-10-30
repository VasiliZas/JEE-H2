package web.vasilizas.repositories;

import java.util.Optional;

public interface Repository<T> {
    void findAll();

    Optional<T> find(int id);

    T save(T entity);

    Optional<T> remove(T entity);
}
