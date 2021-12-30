package web.vasilizas.repositories.orm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import vasilizas.bean.db.MyAbstractEntity;
import web.vasilizas.repositories.interfaces.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public abstract class AbstractRepositoryOrm<T extends MyAbstractEntity> implements Repository<T> {
    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();
    protected Class<T> clazz;
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<T> findAll() {
        begin();
        List<T> resultList = getEm().createQuery("from " + clazz.getName(), clazz).getResultList();
        commit();
        return resultList;
    }

    @Override
    public Optional<T> find(int id) {
        begin();
        Optional<T> optionalEntity = Optional.ofNullable(getEm().find(clazz, id));
        commit();
        return optionalEntity;
    }


    public T save(T entity) {
        begin();
        if (entity.getId() == null) {
            getEm().persist(entity);
        } else {
            getEm().merge(entity);
        }
        commit();
        return entity;
    }

    @Override
    public void remove(int id) {
//        begin();
//        Optional<T> foundEntityOptional = find(entity.getId());
//        if (foundEntityOptional.isPresent()) {
//            getEm().remove(entity);
//            commit();
//
//        }
//        commit();
//
    }

    public EntityManager getEm() {
        if (em.get() == null) {
            em.set(emf.createEntityManager());
        }
        return em.get();
    }

    public void begin() {
        getEm().getTransaction().begin();
    }

    public void commit() {
        getEm().getTransaction().commit();
    }

    public void rollBack() {
        getEm().getTransaction().rollback();
    }
}

