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

    private static volatile JpaStudentRepository instance;

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
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(new Marks().withStudentId(id).withTheme(theme).withGrade(mark).withGroup(group));
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }

    }

    public void removeMarks(int id) {
        StudentDb user;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            user = em.find(StudentDb.class, id);
            em.remove(user.getGrade());
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException | IllegalArgumentException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
    }

    public void removeThemeMarks(int id, String theme, String groups) {

        List<Marks> marks;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
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
            em.close();
        } catch (Exception exception) {
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
}
