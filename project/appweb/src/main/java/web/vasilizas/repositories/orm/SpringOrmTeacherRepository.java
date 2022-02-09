package web.vasilizas.repositories.orm;

import static vasilizas.myservice.person.TeacherService.getAverage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vasilizas.bean.db.Group;
import vasilizas.bean.db.Salary;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.interfaces.TeacherRepository;

@Repository
@RequiredArgsConstructor
public class SpringOrmTeacherRepository implements TeacherRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addPersonInDb(TeacherDb teacherDb) {
        em.persist(teacherDb);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TeacherDb save(TeacherDb entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Optional<TeacherDb> find(int id) {
        return Optional.of(em.find(TeacherDb.class, id));
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<TeacherDb> findAll() {
        return em.createQuery("from TeacherDb ", TeacherDb.class).getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeSalary(int id) {
        em.remove(find(id).orElseThrow(MyWebAppException::new).getSalary());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeGroup(int id) {
        em.remove(em.find(Group.class, id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void remove(int id) {
        em.remove(em.find(TeacherDb.class, id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public double getAvgTeachersSalary(int id, int number) {
        List<BigDecimal> salary = new ArrayList<>();
        var userSalary = em.createQuery("from Salary where sid = ?1", Salary.class)
            .setParameter(1, id).getResultList();
        for (Salary s : userSalary) {
            salary.add(s.getSalary());
        }
        return getAverage(salary, number);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addTeachersSalary(TeacherDb teacherDb, double salary) {
        em.persist(new Salary().withTid(teacherDb.getId()).withSalary(BigDecimal.valueOf(salary)));
    }
}
