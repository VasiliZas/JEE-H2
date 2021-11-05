package web.vasilizas.repositories.jpa;

import vasilizas.bean.db.MyAbstractEntity;
import vasilizas.exception.MyWebAppException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

public interface Repository<T extends MyAbstractEntity> {
    List<T> findAll();

    Optional<T> find(int id);

    void remove(int id);

    default void addPersonInDb(Class<? extends MyAbstractEntity> entity) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error("Exception add : ", exception);
        }
    }

}
