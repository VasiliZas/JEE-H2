package web.vasilizas.repositories.orm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import web.vasilizas.repositories.interfaces.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringOrmStudentRepository implements StudentRepository {

    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addPersonInDb(StudentDb studentDb) {

    }

    @Override
    public Optional<StudentDb> find(int id) {
        return Optional.empty();
    }

    @Override
    public List<StudentDb> findAll() {
        return null;
    }

    @Override
    public void removeMarks(int id) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void addStudentMarks(String theme, int mark, int id, String group) {

    }

    @Override
    public List<Marks> getStudentMarks(StudentDb studentDb) {
        begin();
        var resultList = getEm().createQuery("from Marks where stuid = ?1", Marks.class)
                .setParameter(1, studentDb.getId()).getResultList();
        commit();
        return resultList;
    }

    @Override
    public void removeThemeMarks(int id, String theme, String groups) {

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
