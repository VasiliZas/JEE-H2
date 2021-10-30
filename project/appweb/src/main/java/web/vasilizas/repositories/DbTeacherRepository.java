package web.vasilizas.repositories;

import vasilizas.bean.db.TeacherDb;
import web.vasilizas.controller.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class DbTeacherRepository {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";

    private DbTeacherRepository() {
        //singleton
    }

    public static DbTeacherRepository getInstance() {
        return SingletonHelper.instance;
    }

    public List findAll() {
        List<TeacherDb> myTeacherDbList = new LinkedList<>();
        var sql = "select * from my.teacher";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                myTeacherDbList.add(
                        new TeacherDb().withName(rs.getString(NAME))
                                .withLogin(rs.getString(LOGIN))
                                .withPassword(rs.getString(PASSWORD))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (SQLException e) {
            myLogger.error(e.getMessage());
        }
        return myTeacherDbList;
    }

    public Optional find(int id) {
        TeacherDb user = new TeacherDb();
        var sql = "select * from my.teacher where id = ?";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.withName(rs.getString(NAME))
                        .withLogin(rs.getString(LOGIN))
                        .withPassword(rs.getString(PASSWORD))
                        .withAge(rs.getInt(AGE))
                        .withId(rs.getInt(ID));
            }
        } catch (SQLException e) {
            myLogger.error(e.getMessage());
        }
        return Optional.of(user);
    }

    public void addTeacherInDb(TeacherDb teacherDb) {
        var sql = "insert into my.teacher (id, name , login, password, age ) values (?, ?, ?, ?, ?)";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, teacherDb.getId());
            ps.setString(2, teacherDb.getName());
            ps.setString(3, teacherDb.getLogin());
            ps.setString(4, teacherDb.getPassword());
            ps.setInt(5, teacherDb.getAge());
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate {} ", result);
        } catch (SQLException e) {
            myLogger.error(e.getMessage());
        }
    }

    public Optional remove(Object entity) {
        return Optional.empty();
    }

    private static class SingletonHelper {
        private static final DbTeacherRepository instance = new DbTeacherRepository();
    }
}
