package web.vasilizas.controller;

import vasilizas.bean.db.StudentDb;
import vasilizas.bean.db.TeacherDb;
import vasilizas.exception.MyWebAppException;

import java.sql.*;

import static vasilizas.repository.StudentDbRepository.studentDbList;
import static vasilizas.repository.TeacherDbRepository.teacherDbList;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class DataBase {
    private static final String URL = "jdbc:postgresql://localhost:5432/itdatabase";
    private static final String USER = "vasili";
    private static final String PASSWORD = "123";
    private static final String AGE = "age";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORDT = "password";
    private static final String ID = "id";

    public DataBase() {
        //hh
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void getPersonFromDb() {

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("select name, age, login, password, id from my.student");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                studentDbList.add(new StudentDb(
                        rs.getString(NAME),
                        rs.getInt(AGE),
                        rs.getString(LOGIN),
                        rs.getString(PASSWORDT),
                        rs.getInt(ID)));
            }
        } catch (MyWebAppException | SQLException e) {
            myLogger.error("Connection error ", e);
        }

        try (Connection con2 = getConnection();
             PreparedStatement ps2 = con2.prepareStatement("select name, age, login, password, id from my.teacher");
             ResultSet rs2 = ps2.executeQuery()) {
            while (rs2.next()) {
                teacherDbList.add(new TeacherDb(
                        rs2.getString(NAME),
                        rs2.getInt(AGE),
                        rs2.getString(LOGIN),
                        rs2.getString(PASSWORDT),
                        rs2.getInt(ID)));
            }
        } catch (MyWebAppException | SQLException e) {
            myLogger.error("Connection error ", e);
        }
    }
}
