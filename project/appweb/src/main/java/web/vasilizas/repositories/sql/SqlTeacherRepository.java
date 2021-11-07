package web.vasilizas.repositories.sql;

import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.controller.dataBase.DataBase;
import web.vasilizas.repositories.interfaces.TeacherRepository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static vasilizas.myservice.person.TeacherService.getAverage;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class SqlTeacherRepository implements TeacherRepository {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";

    private SqlTeacherRepository() {
        //singleton
    }

    public static SqlTeacherRepository getInstance() {
        return SingletonHelper.instance;
    }

    public void removeSalary(int id) {
        var sql = "delete from my.salary where id = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error remove: ", e);
        }
    }

    @Override
    public List<TeacherDb> findAll() {
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
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error find all: ", e);
        }
        return myTeacherDbList;
    }

    @Override
    public Optional<TeacherDb> find(int id) {
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
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error find: ", e);
        }
        return Optional.of(user);
    }

    public void addPersonInDb(TeacherDb teacherDb) {
        var sql = "insert into my.teacher (id, name , login, password, age ) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)
        ) {
            DataBase.getInstance().addParameterInSql(teacherDb, ps);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate add {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error add: ", e);
        }
    }

    @Override
    public void remove(int id) {
        removeSalary(id);
        var sql = "delete from my.teacher where id = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate remove {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error remove: ", e);
        }
    }

    public double getAvgTeachersSalary(int id, int number) {
        List<BigDecimal> salary = new LinkedList<>();
        var sql = "select * from my.salary where id = ?";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salary.add(valueOf(rs.getLong("salary")));
            }
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error get salary: ", e);
        }
        return getAverage(salary, number);
    }

    public void addTeachersSalary(TeacherDb teacherDb, double salary) {
        var sql = "insert into my.salary (id, salary ) values (?, ?)";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)
        ) {
            ps.setInt(1, teacherDb.getId());
            ps.setBigDecimal(2, BigDecimal.valueOf(salary));
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate {} ", result);
        } catch (SQLException | MyWebAppException e) {
            myLogger.error("Error set salary: ", e);
        }
    }

    private static class SingletonHelper {
        private static final SqlTeacherRepository instance = new SqlTeacherRepository();
    }
}
