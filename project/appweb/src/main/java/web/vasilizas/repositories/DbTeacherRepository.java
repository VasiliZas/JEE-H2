package web.vasilizas.repositories;

import vasilizas.bean.db.TeacherDb;
import web.vasilizas.controller.dataBase.DataBase;

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
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql);
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
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)
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
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)
        ) {
            DataBase.getInstance().addParameterInSql(teacherDb, ps);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate {} ", result);
        } catch (SQLException e) {
            myLogger.error("Error !!!!  {} ", e);
        }
    }

    public Optional remove(TeacherDb teacherDb) {
        return Optional.empty();
    }

    private static class SingletonHelper {
        private static final DbTeacherRepository instance = new DbTeacherRepository();
    }
}
