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
    private static final String PASSWORDT = "password";
    private static final String ID = "id";

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
                                .withPassword(rs.getString(PASSWORDT))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (SQLException e) {
            myLogger.error(e.getMessage());
        }
        return myStudentDbList;
    }

    public Optional find(int id) {
        List<StudentDb> myStudentDblist = new LinkedList<>();
        var sql = "select * from my.student where id = ?";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                myStudentDblist.add(
                        new StudentDb().withName(rs.getString(NAME))
                                .withLogin(rs.getString(LOGIN))
                                .withPassword(rs.getString(PASSWORDT))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (SQLException e) {
            myLogger.error(e.getMessage());
        }
        return Optional.ofNullable(myStudentDblist.get(0));
    }

    public Object save(Object entity) {
        return null;
    }

    public Optional remove(Object entity) {
        return Optional.empty();
    }
}
