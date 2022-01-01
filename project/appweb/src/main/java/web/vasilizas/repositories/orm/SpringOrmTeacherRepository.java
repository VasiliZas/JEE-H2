package web.vasilizas.repositories.orm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vasilizas.bean.db.Salary;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.interfaces.TeacherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static vasilizas.myservice.person.TeacherService.getAverage;

@Repository
@RequiredArgsConstructor
public class SpringOrmTeacherRepository implements TeacherRepository {

    /*
    private static volatile SpringOrmTeacherRepository instance;
    private SpringOrmTeacherRepository() {
        //singleton
    }

    public static SpringOrmTeacherRepository getInstance() {
        if (instance == null) {
            synchronized (SpringOrmTeacherRepository.class) {
                if (instance == null) {
                    instance = new SpringOrmTeacherRepository();
                }
            }
        }
        return instance;
    }
*/
    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addPersonInDb(TeacherDb teacherDb) {
        try {
            begin();
            getEm().persist(teacherDb);
            commit();
        } catch (Exception ex) {
            rollBack();
            throw new MyWebAppException(ex.getMessage());
        } finally {
            getEm().close();
        }
    }

    public TeacherDb save(TeacherDb entity) {
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
    public Optional<TeacherDb> find(int id) {
        TeacherDb user;
        try {
            begin();
            user = getEm().find(TeacherDb.class, id);
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
    public List<TeacherDb> findAll() {
        List<TeacherDb> dbList;
        try {
            begin();
            dbList = getEm().createQuery("from TeacherDb ", TeacherDb.class).getResultList();
            commit();
        } catch (Exception e) {
            rollBack();
            throw new MyWebAppException(e.getMessage());
        } finally {
            getEm().close();
        }
        return dbList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeSalary(int id) {
        List<Salary> salary;
        salary = find(id).orElseThrow(MyWebAppException::new).getSalary();
        try {
            begin();
            getEm().remove(salary);
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
            var teacherDb = getEm().find(TeacherDb.class, id);
            getEm().remove(teacherDb);
            commit();
        } catch (Exception exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
    }

    @Override
    public double getAvgTeachersSalary(int id, int number) {
        List<BigDecimal> salary = new ArrayList<>();
        try {
            begin();
            TypedQuery<Salary> fromSalary = getEm().createQuery("from Salary where sid = ?1", Salary.class);
            fromSalary.setParameter(1, id);
            var userSalary = fromSalary.getResultList();
            commit();
            for (Salary s : userSalary) {
                salary.add(s.getSalary());
            }
        } catch (MyWebAppException | PersistenceException exception) {
            rollBack();
            throw new MyWebAppException(exception.getMessage());
        } finally {
            getEm().close();
        }
        return getAverage(salary, number);
    }

    @Override
    public void addTeachersSalary(TeacherDb teacherDb, double salary) {
        try {
            begin();
            getEm().persist(new Salary().withTid(teacherDb.getId()).withSalary(BigDecimal.valueOf(salary)));
            commit();
        } catch (Exception ex) {
            rollBack();
            throw new MyWebAppException(ex.getMessage());
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
