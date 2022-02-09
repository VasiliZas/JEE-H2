package web.vasilizas.repositories.jpa;

import org.springframework.stereotype.Repository;
import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;
import web.vasilizas.repositories.interfaces.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaStudentRepository implements StudentRepository {

    private static volatile JpaStudentRepository instance;
    EntityManager em;
    EntityTransaction tx;

    private JpaStudentRepository() {
        //singleton
    }

    public static JpaStudentRepository getInstance() {
        if (instance == null) {
            synchronized (JpaStudentRepository.class) {
                if (instance == null) {
                    instance = new JpaStudentRepository();
                }
            }
        }
        return instance;
    }

    public void addStudentMarks(String theme, int mark, int id, String group) {
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(new Marks().withStudentId(id).withTheme(theme).withGrade(mark).withGroup(group));
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeMarks(int id) {
        StudentDb user;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            user = em.find(StudentDb.class, id);
            em.remove(user.getGrade());
            tx.commit();
        } catch (MyWebAppException | PersistenceException | IllegalArgumentException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeThemeMarks(int id, String theme, String groups) {

        List<Marks> marks;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            TypedQuery<Marks> fromMarks = em.createQuery("from Marks where stuid = ?1", Marks.class)
                    .setParameter(1, id);
            marks = fromMarks.getResultList();
            Marks userMarks = null;
            for (Marks mark : marks) {
                if (mark.getGroups().equals(groups) && mark.getTheme().equals(theme))
                    userMarks = mark;
            }
            em.remove(userMarks);
            tx.commit();
        } catch (Exception exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<StudentDb> findAll() {
        List<StudentDb> dbList;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            TypedQuery<StudentDb> fromStudentDb = em.createQuery("from StudentDb ", StudentDb.class);
            dbList = fromStudentDb.getResultList();
            tx.commit();
        } catch (MyWebAppException | PersistenceException e) {
            tx.rollback();
            throw new MyWebAppException(e.getMessage());
        } finally {
            em.close();
        }
        return dbList;
    }

    @Override
    public Optional<StudentDb> find(int id) {
        StudentDb user;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            user = em.find(StudentDb.class, id);
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
        return Optional.of(user);
    }


    public void addPersonInDb(StudentDb studentDb) {
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(studentDb);
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(int id) {
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            var student = em.find(StudentDb.class, id);
            em.remove(student);
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Marks> getStudentMarks(StudentDb studentDb) {
        List<Marks> marks;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            TypedQuery<Marks> fromMarks = em.createQuery("from Marks where stuid = ?1", Marks.class)
                    .setParameter(1, studentDb.getId());
            marks = fromMarks.getResultList();
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
        return marks;
    }
}
