package web.vasilizas.controller.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.MyAbstractEntity;
import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static vasilizas.repository.StudentDbRepository.studentDbList;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;

public class DataBase<T extends MyAbstractEntity> {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static volatile DataBase instance;

    private DataBase() {
        //singleton
    }

    private final Logger myLogger = LoggerFactory.getLogger(DataBase.class);

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

    public List<StudentDb> getStudentFromDb(String personName) {
        var sql = "select * from my.student where name = ?";
        try (PreparedStatement ps = connectionDataBase(sql)) {
            ps.setString(1, personName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                studentDbList.add(
                        new StudentDb().withName(rs.getString(NAME))
                                .withLogin(rs.getString(LOGIN))
                                .withPassword(rs.getString(PASSWORD))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (MyWebAppException | SQLException e) {
            myLogger.error("Connection error ", e);
            throw new MyWebAppException(e.getMessage());
        }
        return studentDbList;
    }

    public List<TeacherDb> getTeacherFromDb(String personName) {
        var sql = "select * from my.teacher where name = ?";
        try (PreparedStatement ps2 = connectionDataBase(sql)) {
            ps2.setString(1, personName);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                teacherDbList.add(new TeacherDb().withName(rs2.getString(NAME))
                        .withLogin(rs2.getString(LOGIN))
                        .withPassword(rs2.getString(PASSWORD))
                        .withAge(rs2.getInt(AGE))
                        .withId(rs2.getInt(ID)));
            }
        } catch (MyWebAppException | SQLException e) {
            myLogger.error("Connection error ", e);
            throw new MyWebAppException(e.getMessage());
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
