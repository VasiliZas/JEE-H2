package web.vasilizas.repositories.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.Marks;
import vasilizas.bean.db.StudentDb;
import vasilizas.exception.MyWebAppException;
import web.vasilizas.controller.database.DataBase;
import web.vasilizas.repositories.interfaces.StudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SqlStudentRepository implements StudentRepository {

    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private final Logger myLogger = LoggerFactory.getLogger(SqlStudentRepository.class);

    private SqlStudentRepository() {
        //singleton
    }

    public static SqlStudentRepository getInstance() {
        return SingletonHelper.instance;
    }

    public void addStudentMarks(String theme, int mark, int id) {
        var sql = "insert into my.grade (id, theme, grade, stuid ) values (?, ?, ?, ?)";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, mark + id);
            ps.setString(2, theme);
            ps.setInt(3, mark);
            ps.setInt(4, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate add {} ", result);
        } catch (SQLException | MyWebAppException e) {
            throw new MyWebAppException(e.getMessage());
        }

    }

    public void removeMarks(int id) {
        var sql = "delete from my.grade where stuid = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate remove marks {} ", result);
        } catch (SQLException | MyWebAppException e) {
            throw new MyWebAppException(e.getMessage());
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
            throw new MyWebAppException(e.getMessage());
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
            throw new MyWebAppException(e.getMessage());
        }
        return Optional.of(user);
    }

    @Override
    public void remove(int id) {
        var sql = "delete from my.student where id = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate {} ", result);
        } catch (SQLException | MyWebAppException e) {
            throw new MyWebAppException(e.getMessage());
        }
    }

    public void addPersonInDb(StudentDb studentDb) {
        var sql = "insert into my.student (id, name , login, password, age ) values (?, ?, ?, ?, ?)";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            DataBase.getInstance().addParameterInSql(studentDb, ps);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate add {} ", result);
        } catch (SQLException | MyWebAppException e) {
            throw new MyWebAppException(e.getMessage());
        }
    }

    public List<Marks> getStudentMarks(StudentDb studentDb) {
        List<Marks> marks = new ArrayList<>();
        var sql = "select * from my.grade where stuid = ?";
        try (var ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, studentDb.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marks.add(new Marks().withStudentId(rs.getInt(ID))
                        .withGrade(rs.getInt("grade"))
                        .withTheme(rs.getString("theme")));
            }
        } catch (SQLException | MyWebAppException e) {
            throw new MyWebAppException(e.getMessage());
        }
        return marks;
    }

    @Override
    public void removeThemeMarks(int id, String theme) {
        var sql = "delete from my.grade where stuid = ? and theme = ?";
        try (PreparedStatement ps = DataBase.getInstance().connectionDataBase(sql)) {
            ps.setInt(1, id);
            ps.setString(2, theme);
            var result = ps.executeUpdate();
            myLogger.info("Result executeUpdate remove marks {} ", result);
        } catch (SQLException | MyWebAppException e) {
            throw new MyWebAppException(e.getMessage());
        }
    }

    private static class SingletonHelper {
        private static final SqlStudentRepository instance = new SqlStudentRepository();
    }
}
