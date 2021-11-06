package web.vasilizas.repositories.jpa;

import vasilizas.bean.db.Salary;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;
import web.vasilizas.repositories.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static vasilizas.myservice.person.TeacherService.getAverage;

public class DbTeacherRepository implements Repository<TeacherDb> {

    private DbTeacherRepository() {
        //singleton
    }

    public static DbTeacherRepository getInstance() {
        return SingletonHelper.instance;
    }

    public static void removeSalary(int id) {
        List<Salary> salary;
        DbTeacherRepository.getInstance().find(id).orElseThrow(MyWebAppException::new);
        salary = DbTeacherRepository.getInstance().find(id).get().getSalary();
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(salary);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
    }


    public List<Salary> getAllSalary() {
        List<Salary> userSalary;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<Salary> fromSalary = em.createQuery("from Salary ", Salary.class);
            userSalary = fromSalary.getResultList();
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
        }
        return userSalary;
    }

    @Override
    public List<TeacherDb> findAll() {
        List<TeacherDb> dbList;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<TeacherDb> fromTeacherDb = em.createQuery("from TeacherDb ", TeacherDb.class);
            dbList = fromTeacherDb.getResultList();
        } catch (MyWebAppException | PersistenceException e) {
            throw new MyWebAppException(e.getMessage());
        }
        return dbList;
    }

    @Override
    public Optional<TeacherDb> find(int id) {
        TeacherDb user;
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            user = em.find(TeacherDb.class, id);
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            throw new MyWebAppException(exception.getMessage());
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
            throw new MyWebAppException(ex.getMessage());
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
            throw new MyWebAppException(ex.getMessage());
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
            throw new MyWebAppException(exception.getMessage());
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
            throw new MyWebAppException(exception.getMessage());
        }
        return getAverage(salary, number);
    }

    private static class SingletonHelper {
        private static final DbTeacherRepository instance = new DbTeacherRepository();
    }
}
