package web.vasilizas.repositories.jpa;

import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.*;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

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
            myLogger.error("Exception remove marks: ", exception);
        }
    }

    @Override
    public List<StudentDb> findAll() {
        List<StudentDb> dbList = new ArrayList<>();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<StudentDb> fromStudentDb = em.createQuery("from StudentDb ", StudentDb.class);
            dbList = fromStudentDb.getResultList();
        } catch (MyWebAppException | PersistenceException e) {
            myLogger.error("Error find all : ", e);
        }
        return dbList;
    }

    @Override
    public Optional<StudentDb> find(int id) {
        StudentDb user = new StudentDb();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            user = em.find(StudentDb.class, id);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error("Exception find : ", exception);
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
            myLogger.error("Exception add : ", exception);
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
            myLogger.error("Exception remove : ", exception);
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
            myLogger.error("Exception get student marks : ", exception);
        }
        return userMarks;
    }

    private static class SingletonHelper {
        private static final DbStudentRepository instance = new DbStudentRepository();
    }
}
