package web.vasilizas.repositories.jpa;

import org.springframework.stereotype.Repository;
import vasilizas.bean.db.Salary;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.EntityManagerHelper;
import web.vasilizas.repositories.interfaces.TeacherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static vasilizas.myservice.person.TeacherService.getAverage;

@Repository
public class JpaTeacherRepository implements TeacherRepository {

    private static volatile JpaTeacherRepository instance;
    EntityManager em;
    EntityTransaction tx;

    private JpaTeacherRepository() {
        //singleton
    }

    public static JpaTeacherRepository getInstance() {
        if (instance == null) {
            synchronized (JpaTeacherRepository.class) {
                if (instance == null) {
                    instance = new JpaTeacherRepository();
                }
            }
        }
        return instance;
    }

    public void removeSalary(int id) {
        List<Salary> salary;
        salary = JpaTeacherRepository.getInstance().find(id).orElseThrow(MyWebAppException::new).getSalary();
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.remove(salary);
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }


    public List<Salary> getAllSalary() {
        List<Salary> userSalary;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            TypedQuery<Salary> fromSalary = em.createQuery("from Salary ", Salary.class);
            userSalary = fromSalary.getResultList();
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
        return userSalary;
    }

    @Override
    public List<TeacherDb> findAll() {
        List<TeacherDb> dbList;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            TypedQuery<TeacherDb> fromTeacherDb = em.createQuery("from TeacherDb ", TeacherDb.class);
            dbList = fromTeacherDb.getResultList();
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
    public Optional<TeacherDb> find(int id) {
        TeacherDb user;
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            user = em.find(TeacherDb.class, id);
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
        return Optional.of(user);
    }

    public void addPersonInDb(TeacherDb teacherDb) {
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(teacherDb);
            tx.commit();
        } catch (MyWebAppException | PersistenceException ex) {
            tx.rollback();
            throw new MyWebAppException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void addTeachersSalary(TeacherDb teacherDb, double salary) {
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(new Salary().withTid(teacherDb.getId()).withSalary(BigDecimal.valueOf(salary)));
            tx.commit();
        } catch (MyWebAppException | PersistenceException ex) {
            tx.rollback();
            throw new MyWebAppException(ex.getMessage());
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
            var teacherDb = em.find(TeacherDb.class, id);
            em.remove(teacherDb);
            tx.commit();
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
    }

    public double getAvgTeachersSalary(int id, int number) {
        List<BigDecimal> salary = new LinkedList<>();
        try {
            em = EntityManagerHelper.getInstance().getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            TypedQuery<Salary> fromSalary = em.createQuery("from Salary where sid = ?1", Salary.class);
            fromSalary.setParameter(1, id);
            var userSalary = fromSalary.getResultList();
            tx.commit();
            for (Salary s : userSalary) {
                salary.add(s.getSalary());
            }
        } catch (MyWebAppException | PersistenceException exception) {
            tx.rollback();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            em.close();
        }
        return getAverage(salary, number);
    }
}
