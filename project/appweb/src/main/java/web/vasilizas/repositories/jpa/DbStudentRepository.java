package web.vasilizas.repositories.jpa;

import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;
import web.vasilizas.repositories.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DbStudentRepository implements Repository<StudentDb> {

    private DbStudentRepository() {
        //singleton
    }

    public static DbStudentRepository getInstance() {
        return SingletonHelper.instance;
    }

    public static void removeMarks(int id) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Marks marks = em.find(Marks.class, id);
            em.remove(marks);
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

    public Map<String, Integer> getStudentMarks(StudentDb studentDb) {
        Map<String, Integer> userMarks = new LinkedHashMap<>();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Marks> fromMarks = em.createQuery("from Marks where stuid = ?1", Marks.class)
                    .setParameter(1, studentDb.getId());
            var marks = fromMarks.getResultList();
            tx.commit();
            em.close();
            for (Marks m : marks) {
                userMarks.put(m.getTheme(), m.getGrade());
            }
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
        return userMarks;
    }

    private static class SingletonHelper {
        private static final DbStudentRepository instance = new DbStudentRepository();
    }
}
