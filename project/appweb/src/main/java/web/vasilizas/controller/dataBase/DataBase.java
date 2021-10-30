package web.vasilizas.controller.dataBase;

import vasilizas.bean.db.MyAbstractEntity;
import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.controller.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static vasilizas.repository.StudentDbRepository.studentDbList;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class DataBase<T extends MyAbstractEntity> {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";

    private DataBase() {
        //singleton
    }

    public static DataBase getInstance() {
        return SingletonHelper.instance;
    }

    public PreparedStatement connectionDataBase(String sql) throws SQLException {
        Connection con = MyConnectionPool.getInstance().getConnection();
        return con.prepareStatement(sql);
    }

    public void getStudentFromDb(String personName) {
        var sql = "select * from my.student where name = ?";
        try (PreparedStatement ps = connectionDataBase(sql)
        ) {
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
        }
    }

    public void getTeacherFromDb(String personName) {
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
        }
    }

    public void addParameterInSql(T entity, PreparedStatement ps) throws SQLException {
        ps.setInt(1, entity.getId());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getLogin());
        ps.setString(4, entity.getPassword());
        ps.setInt(5, entity.getAge());
    }

    private static class SingletonHelper {
        private static final DataBase instance = new DataBase();
    }
}
