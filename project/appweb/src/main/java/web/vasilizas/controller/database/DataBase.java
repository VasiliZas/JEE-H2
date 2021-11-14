package web.vasilizas.controller.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.MyAbstractEntity;
import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import vasilizas.repository.StudentDbRepository;
import web.vasilizas.repositories.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static vasilizas.repository.TeacherDbRepository.teacherDbList;

public class DataBase<T extends MyAbstractEntity> {

    private static volatile DataBase instance;
    private final Logger myLogger = LoggerFactory.getLogger(DataBase.class);

    private DataBase() {
        //singleton
    }

    public static DataBase getInstance() {
        if (instance == null) {
            synchronized (DataBase.class) {
                if (instance == null) {
                    instance = new DataBase();
                }
            }
        }
        return instance;
    }

    public PreparedStatement connectionDataBase(String sql) throws SQLException {
        Connection con = MyConnectionPool.getInstance().getConnection();
        return con.prepareStatement(sql);
    }

    public List<StudentDb> getStudentFromDb(String personName, String personLogin) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<StudentDb> fromStudent = em.createQuery("from StudentDb where name = ?1 and login = ?2", StudentDb.class)
                    .setParameter(1, personName)
                    .setParameter(2, personLogin);
            StudentDbRepository.studentDbList = fromStudent.getResultList();
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error(" ", exception);
            throw new MyWebAppException(exception.getMessage());
        }
        return StudentDbRepository.studentDbList;
    }

    public List<TeacherDb> getTeacherFromDb(String personName, String personLogin) {
        try {
            EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            TypedQuery<TeacherDb> fromTeacher = em.createQuery("from TeacherDb where name = ?1 and login = ?2", TeacherDb.class)
                    .setParameter(1, personName)
                    .setParameter(2, personLogin);
            teacherDbList = fromTeacher.getResultList();
            tx.commit();
            em.close();
        } catch (MyWebAppException | PersistenceException exception) {
            myLogger.error(" ", exception);
            throw new MyWebAppException(exception.getMessage());
        }
        return teacherDbList;
    }

    public void addParameterInSql(T entity, PreparedStatement ps) throws SQLException {
        ps.setInt(1, entity.getId());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getLogin());
        ps.setString(4, entity.getPassword());
        ps.setInt(5, entity.getAge());
    }
}
