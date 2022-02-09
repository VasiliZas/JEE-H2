package web.vasilizas.repositories.orm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vasilizas.bean.db.Group;
import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.interfaces.StudentRepository;

@Repository
@RequiredArgsConstructor
public class SpringOrmStudentRepository implements StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    @Override
    public void addPersonInDb(StudentDb studentDb) {
        em.persist(studentDb);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public StudentDb save(StudentDb entity) {
        try {
            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new MyWebAppException(e.getMessage());
        }
        return entity;
    }

    @Transactional(rollbackFor = Exception.class,
        readOnly = true,
        isolation = Isolation.READ_COMMITTED)
    @Override
    public Optional<StudentDb> find(int id) {
        return Optional.of(em.find(StudentDb.class, id));
    }

    @Transactional(rollbackFor = Exception.class,
        readOnly = true,
        isolation = Isolation.READ_COMMITTED)
    @Override
    public List<StudentDb> findAll() {
        return em.createQuery("from  StudentDb", StudentDb.class).getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    public void removeMarks(int id) {
        em.remove(em.find(StudentDb.class, id).getGrade());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    public void remove(int id) {
        em.remove(em.find(StudentDb.class, id));
    }

    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    @Override
    public void addStudentMarks(String theme, int mark, int id, String group) {
        em.persist(new Marks().withStudentId(id).withTheme(theme).withGrade(mark).withGroup(group));
    }

    @Override
    @Transactional(readOnly = true,
        isolation = Isolation.READ_COMMITTED)
    public List<Marks> getStudentMarks(StudentDb studentDb) {
        try {
            return em.createQuery("from Marks where stuid = ?1", Marks.class)
                .setParameter(1, studentDb.getId()).getResultList();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new MyWebAppException(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    @Override
    public void removeThemeMarks(int id, String theme, String groups) {
        List<Marks> marks = em.createQuery("from Marks where stuid = ?1", Marks.class)
            .setParameter(1, id).getResultList();
        Marks userMarks = null;
        for (Marks mark : marks) {
            if (mark.getGroups().equals(groups) && mark.getTheme().equals(theme)) {
                userMarks = mark;
            }
        }
        em.remove(userMarks);
    }

    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    public StudentDb addGroup(int id, int groupId) {
        StudentDb student = em.find(StudentDb.class, id);
        var group = em.find(Group.class, groupId);
        List<Group> groups = student.getGroups();
        groups.add(group);
        student.setGroups(groups);
        em.merge(student);
        return student;
    }

    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = Exception.class,
        isolation = Isolation.SERIALIZABLE)
    public StudentDb removeGroup(int id, int groupId) {
        StudentDb student = em.find(StudentDb.class, id);
        List<Group> groups = student.getGroups();
        groups.removeIf(group -> group.getId() == groupId);
        student.setGroups(groups);
        em.merge(student);
        return student;
    }
}
