package web.vasilizas.repositories.orm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.interfaces.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringOrmStudentRepository implements StudentRepository {


//    private static volatile SpringOrmStudentRepository instance;
//    private SpringOrmStudentRepository() {
//        //singleton
//    }
//
//    public static SpringOrmStudentRepository getInstance() {
//        if (instance == null) {
//            synchronized (SpringOrmStudentRepository.class) {
//                if (instance == null) {
//                    instance = new SpringOrmStudentRepository();
//                }
//            }
//        }
//        return instance;
//    }

    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addPersonInDb(StudentDb studentDb) {
        try {
            begin();
            getEm().persist(studentDb);
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
    }

    public StudentDb save(StudentDb entity) {
        try {
            begin();
            if (entity.getId() == null) {
                getEm().persist(entity);
            } else {
                getEm().merge(entity);
            }
            commit();
        } catch (Exception e) {
            rollBack();
            throw new MyWebAppException(e.getMessage());
        } finally {
            getEm().close();
        }
        return entity;
    }

    @Override
    public Optional<StudentDb> find(int id) {
        StudentDb user;
        try {
            begin();
            user = getEm().find(StudentDb.class, id);
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
        return Optional.of(user);
    }

    @Override
    public List<StudentDb> findAll() {
        List<StudentDb> resultList;
        try {
            begin();
            resultList = getEm().createQuery("from  StudentDb", StudentDb.class).getResultList();
            commit();
        } catch (Exception e) {
            rollBack();
            throw new MyWebAppException(e.getMessage());
        } finally {
            getEm().close();
        }
        return resultList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeMarks(int id) {
        StudentDb user;
        try {
            begin();
            user = getEm().find(StudentDb.class, id);
            getEm().remove(user.getGrade());
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(int id) {
        try {
            begin();
            var student = getEm().find(StudentDb.class, id);
            getEm().remove(student);
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
    }

    @Transactional
    @Override
    public void addStudentMarks(String theme, int mark, int id, String group) {
        try {
            begin();
            getEm().persist(new Marks().withStudentId(id).withTheme(theme).withGrade(mark).withGroup(group));
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
    }

    @Override
    public List<Marks> getStudentMarks(StudentDb studentDb) {
        List<Marks> resultList;
        try {
            begin();
            resultList = getEm().createQuery("from Marks where stuid = ?1", Marks.class)
                    .setParameter(1, studentDb.getId()).getResultList();
            commit();
        } catch (Exception e) {
            rollBack();
            throw new MyWebAppException(e.getMessage());
        } finally {
            getEm().close();
        }
        return resultList;
    }

    @Override
    public void removeThemeMarks(int id, String theme, String groups) {
        List<Marks> marks;
        try {
            begin();
            TypedQuery<Marks> fromMarks = getEm().createQuery("from Marks where stuid = ?1", Marks.class)
                    .setParameter(1, id);
            marks = fromMarks.getResultList();
            Marks userMarks = null;
            for (Marks mark : marks) {
                if (mark.getGroups().equals(groups) && mark.getTheme().equals(theme))
                    userMarks = mark;
            }
            getEm().remove(userMarks);
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
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
