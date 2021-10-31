package web.vasilizas.repositories;

import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.controller.dataBase.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class DbStudentRepository implements Repository<StudentDb> {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String S_T = "theme";
    private static final String S_GR = "grade";

    private DbStudentRepository() {
        //singleton
    }

    public static DbStudentRepository getInstance() {
        return SingletonHelper.instance;
    }

    private static void removeMarks(int id) {
        var sql = "delete from my.grade where id = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate remove marks {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error remove: ", e);
        }
    }

    @Override
    public List<StudentDb> findAll() {
        List<StudentDb> myStudentDbList = new LinkedList<>();
        var sql = "select * from my.student";
        try (var ps = DataBase.getInstance().connectionDataBase(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                myStudentDbList.add(
                        new StudentDb().withName(rs.getString(NAME))
                                .withLogin(rs.getString(LOGIN))
                                .withPassword(rs.getString(PASSWORD))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error find all : ", e);
        }
        return myStudentDbList;
    }

    @Override
    public Optional<StudentDb> find(int id) {
        StudentDb user = new StudentDb();
        var sql = "select * from my.student where id = ?";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.withName(rs.getString(NAME))
                        .withLogin(rs.getString(LOGIN))
                        .withPassword(rs.getString(PASSWORD))
                        .withAge(rs.getInt(AGE))
                        .withId(rs.getInt(ID));
            }
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error find: ", e);
        }
        return Optional.of(user);
    }

    public void addPersonInDb(StudentDb studentDb) {
        var sql = "insert into my.student (id, name , login, password, age ) values (?, ?, ?, ?, ?)";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            DataBase.getInstance().addParameterInSql(studentDb, ps);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate add {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error add: ", e);
        }
    }

    @Override
    public void remove(int id) {
        removeMarks(id);
        var sql = "delete from my.student where id = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error remove: ", e);
        }
    }

    public Map<String, Integer> getStudentMarks(StudentDb studentDb) {
        var sql = "select * from my.grade where id = ?";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, studentDb.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                studentDb.addGrade(rs.getString(S_T), rs.getInt(S_GR));
            }
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error get marks: ", e);
        }
        return studentDb.getMarks();
    }

    private static class SingletonHelper {
        private static final DbStudentRepository instance = new DbStudentRepository();
    }
}
