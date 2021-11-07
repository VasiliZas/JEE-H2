package web.vasilizas.repositories.jpa;

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

public class JpaStudentRepository implements StudentRepository {

    private JpaStudentRepository() {
        //singleton
    }

    public static JpaStudentRepository getInstance() {
        return SingletonHelper.instance;
    }

    public void addStudentMarks(String theme, int mark, int id) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(new Marks().withStudentId(id).withTheme(theme).withGrade(mark));
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }

    }

    public void removeMarks(int id) {
        try {
            List<Marks> dbList;
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Marks> fromMarks = em.createQuery("from Marks ", Marks.class);
            dbList = fromMarks.getResultList();
            for (Marks marks : dbList) {
                if (marks.getStuid() == id) {
                    em.remove(marks);
                }
            }
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
    }

    public void removeThemeMarks(int id, String theme) {
        try {
            List<Marks> dbList;
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Marks> fromMarks = em.createQuery("from Marks ", Marks.class);
            dbList = fromMarks.getResultList();
            for (Marks marks : dbList) {
                if (marks.getTheme().equals(theme) && marks.getStuid() == id) {
                    em.remove(marks);
                }
            }
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
    }

    @Override
    public List<StudentDb> findAll() {
        List<StudentDb> dbList;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<StudentDb> fromStudentDb = em.createQuery("from StudentDb ", StudentDb.class);
            dbList = fromStudentDb.getResultList();
        } catch (MyWebAppException | PersistenceException e) {
            throw new MyWebAppException(e.getMessage());
        }
        return dbList;
    }

    @Override
    public Optional<StudentDb> find(int id) {
        StudentDb user;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            user = em.find(StudentDb.class, id);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
        return Optional.of(user);
    }


    public void addPersonInDb(StudentDb studentDb) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(studentDb);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            var student = em.find(StudentDb.class, id);
            em.remove(student);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
    }

    public List<Marks> getStudentMarks(StudentDb studentDb) {
        List<Marks> marks;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Marks> fromMarks = em.createQuery("from Marks where stuid = ?1", Marks.class)
                    .setParameter(1, studentDb.getId());
            marks = fromMarks.getResultList();
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
        return marks;
    }

    private static class SingletonHelper {
        private static final JpaStudentRepository instance = new JpaStudentRepository();
    }
}
