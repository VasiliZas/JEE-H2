package web.vasilizas.repositories.orm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vasilizas.bean.db.TeacherDb;
import web.vasilizas.repositories.interfaces.TeacherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringOrmTeacherRepository implements TeacherRepository {

    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addPersonInDb(TeacherDb teacherDb) {

    }

    @Override
    public Optional<TeacherDb> find(int id) {
        return Optional.empty();
    }

    @Override
    public List<TeacherDb> findAll() {
        begin();
        List<TeacherDb> resultList = getEm().createQuery("from  TeacherDb", TeacherDb.class).getResultList();
        commit();
        return resultList;
    }

    @Override
    public void removeSalary(int id) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double getAvgTeachersSalary(int id, int number) {
        return 0;
    }

    @Override
    public void addTeachersSalary(TeacherDb teacherDb, double salary) {

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
