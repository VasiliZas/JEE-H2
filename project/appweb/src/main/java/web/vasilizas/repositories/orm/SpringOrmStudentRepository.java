package web.vasilizas.repositories.orm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
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

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public void addPersonInDb(StudentDb studentDb) {
        em.persist(studentDb);
    }

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

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Optional<StudentDb> find(int id) {
        return Optional.of(em.find(StudentDb.class, id));
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<StudentDb> findAll() {
        return em.createQuery("from  StudentDb", StudentDb.class).getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeMarks(int id) {
        StudentDb user;
        user = em.find(StudentDb.class, id);
        em.remove(user.getGrade());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void remove(int id) {
        var student = em.find(StudentDb.class, id);
        em.remove(student);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addStudentMarks(String theme, int mark, int id, String group) {
        em.persist(new Marks().withStudentId(id).withTheme(theme).withGrade(mark).withGroup(group));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Marks> getStudentMarks(StudentDb studentDb) {
        List<Marks> resultList;
        try {
            resultList = em.createQuery("from Marks where stuid = ?1", Marks.class)
                .setParameter(1, studentDb.getId()).getResultList();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new MyWebAppException(e.getMessage());
        }
        return resultList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void removeThemeMarks(int id, String theme, String groups) {
        List<Marks> marks;
        TypedQuery<Marks> fromMarks = em.createQuery("from Marks where stuid = ?1", Marks.class)
            .setParameter(1, id);
        marks = fromMarks.getResultList();
        Marks userMarks = null;
        for (Marks mark : marks) {
            if (mark.getGroups().equals(groups) && mark.getTheme().equals(theme)) {
                userMarks = mark;
            }
        }
        em.remove(userMarks);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public StudentDb addGroup(int id, int groupId) {
        List<Group> groups;
        StudentDb student;
        student = em.find(StudentDb.class, id);
        var group = em.find(Group.class, groupId);
        groups = student.getGroups();
        groups.add(group);
        student.setGroups(groups);
        em.merge(student);
        return student;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public StudentDb removeGroup(int id, int groupId) {
        List<Group> groups;
        StudentDb student;
        student = em.find(StudentDb.class, id);
        groups = student.getGroups();
        groups.removeIf(group -> group.getId() == groupId);
        student.setGroups(groups);
        em.merge(student);
        return student;
    }
}
