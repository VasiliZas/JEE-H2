package web.vasilizas.controller.dataBase;

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

public class DataBase {
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORDT = "password";
    private static final String ID = "id";

    private DataBase() {
        //singleton
    }

    public static DataBase getInstance() {
        return SingletonHelper.instance;
    }

    public void getStudentFromDb(String personName) {
        var sql = "select * from my.student where name = ?";
        try (Connection con = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, personName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                studentDbList.add(
                        new StudentDb().withName(rs.getString(NAME))
                                .withLogin(rs.getString(LOGIN))
                                .withPassword(rs.getString(PASSWORDT))
                                .withAge(rs.getInt(AGE))
                                .withId(rs.getInt(ID)));
            }
        } catch (MyWebAppException | SQLException e) {
            myLogger.error("Connection error ", e);
        }
    }

    public void getTeacherFromDb(String personName) {
        try (Connection con2 = MyConnectionPool.getInstance().getConnection();
             PreparedStatement ps2 = con2.prepareStatement("select * from my.teacher where name = ?")) {
            ps2.setString(1, personName);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                teacherDbList.add(new TeacherDb().withName(rs2.getString(NAME))
                        .withLogin(rs2.getString(LOGIN))
                        .withPassword(rs2.getString(PASSWORDT))
                        .withAge(rs2.getInt(AGE))
                        .withId(rs2.getInt(ID)));
            }
        } catch (MyWebAppException | SQLException e) {
            myLogger.error("Connection error ", e);
        }
    }

    private static class SingletonHelper {
        private static final DataBase instance = new DataBase();
    }
}
