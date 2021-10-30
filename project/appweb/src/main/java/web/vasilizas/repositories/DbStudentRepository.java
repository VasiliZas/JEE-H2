package web.vasilizas.repositories;

import vasilizas.bean.db.StudentDb;
import web.vasilizas.controller.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class DbStudentRepository {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";

    private DbStudentRepository() {
        //singleton
    }

    public static DbStudentRepository getInstance() {
        return SingletonHelper.instance;
    }

    public List findAll() {
        List<StudentDb> myStudentDbList = new LinkedList<>();
        var sql = "select * from my.student";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                myStudentDbList.add(
                        new StudentDb().withName(rs.getString(NAME))
                                .withLogin(rs.getString(LOGIN))
                                .withPassword(rs.getString(PASSWORD))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (SQLException e) {
            myLogger.error(e.getMessage());
        }
        return myStudentDbList;
    }

    public Optional find(int id) {
        StudentDb user = new StudentDb();
        var sql = "select * from my.student where id = ?";
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

    public void addStudentInDb(StudentDb studentDb) {
        var sql = "insert into my.student (id, name , login, password, age ) values (?, ?, ?, ?, ?)";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, studentDb.getId());
            ps.setString(2, studentDb.getName());
            ps.setString(3, studentDb.getLogin());
            ps.setString(4, studentDb.getPassword());
            ps.setInt(5, studentDb.getAge());
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
        private static final DbStudentRepository instance = new DbStudentRepository();
    }
}
