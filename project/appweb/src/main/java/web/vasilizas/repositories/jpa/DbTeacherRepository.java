package web.vasilizas.repositories.jpa;

import vasilizas.bean.db.Salary;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static vasilizas.myservice.person.TeacherService.getAverage;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class DbTeacherRepository implements Repository<TeacherDb> {

    private DbTeacherRepository() {
        //singleton
    }

    public static DbTeacherRepository getInstance() {
        return SingletonHelper.instance;
    }

    public static void removeSalary(int id) {
//        try {
//
//            List<Salary> salary = DbTeacherRepository.getInstance().getAllSalary(id);
//            for (Salary list : salary) {
//                if (list.getId() == id) {
//                    em.remove(list);
//                    tx.commit();
//                }
//            }
//
//            em.close();
//        } catch (MyWebAppException | PersistenceException exception) {
//            myLogger.error("Exception remove salary : ", exception);
//        }
    }

    public List<Salary> getAllSalary() {
        List<Salary> userSalary = new LinkedList<>();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Salary> fromSalary = em.createQuery("from Salary ", Salary.class);
            userSalary = fromSalary.getResultList();
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error("Exception find : ", exception);
        }
        return userSalary;
    }

    @Override
    public List<TeacherDb> findAll() {
        List<TeacherDb> dbList = new ArrayList<>();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<TeacherDb> fromTeacherDb = em.createQuery("from TeacherDb ", TeacherDb.class);
            dbList = fromTeacherDb.getResultList();
        } catch (MyWebAppException | PersistenceException e) {
            myLogger.error("Error find all : ", e);
        }
        return dbList;
    }

    @Override
    public Optional<TeacherDb> find(int id) {
        TeacherDb user = new TeacherDb();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            user = em.find(TeacherDb.class, id);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error("Exception find : ", exception);
        }
        return Optional.of(user);
    }

    public void addPersonInDb(TeacherDb teacherDb) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction trx = em.getTransaction();
            trx.begin();
            em.persist(teacherDb);
            trx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException ex) {
            myLogger.error("Exception add : ", ex);
        }
    }

    public void addTeacherSalary(TeacherDb teacherDb, double salary) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction trx = em.getTransaction();
            trx.begin();
            em.persist(new Salary().withTid(teacherDb.getId()).withSalary(BigDecimal.valueOf(salary)));
            trx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException ex) {
            myLogger.error("Exception add : ", ex);
        }
    }

    @Override
    public void remove(int id) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            var teacherDb = em.find(TeacherDb.class, id);
            em.remove(teacherDb);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error("Exception remove : ", exception);
        }
    }

    public double getAvgTeachersSalary(int id, int number) {
        List<BigDecimal> salary = new LinkedList<>();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Salary> fromSalary = em.createQuery("from Salary where sid = ?1", Salary.class);
            fromSalary.setParameter(1, id);
            var userSalary = fromSalary.getResultList();
            tx.commit();
            em.close();
            for (Salary s : userSalary) {
                salary.add(s.getSalary());
            }
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error("Exception get avg salary : ", exception);
        }
        return getAverage(salary, number);
    }

    private static class SingletonHelper {
        private static final DbTeacherRepository instance = new DbTeacherRepository();
    }
}
