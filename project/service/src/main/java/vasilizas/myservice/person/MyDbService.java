//package vasilizas.myservice.person;
//
//
//import vasilizas.bean.db.StudentDb;
//import vasilizas.exception.MyWebAppException;
//import web.vasilizas.controller.MyConnectionPool;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static vasilizas.factory.Factory.createStudent;
//import static vasilizas.factory.Factory.createTeacher;
//import static vasilizas.myservice.person.MyService.log;
//import static vasilizas.repository.StudentDbRepository.studentDbList;
//import static vasilizas.repository.StudentRepository.studentList;
//import static vasilizas.repository.TeacherRepository.teacherList;
//import static web.vasilizas.controller.authentication.Authentication.myLogger;
//
//public class MyDbService {
//
//    private MyDbService() {
//        // blank default constructor for utility class
//    }
//
//    public static MyDbService getInstance() {
//        return SingletonHelper.myService;
//    }
//
//    public void createAndAddPerson(String typePerson, String namePerson, int agePerson, String login, String password) {
//
//        switch (typePerson) {
//            case "Student" -> studentList.add(createStudent(namePerson, agePerson, login, password));
//            case "Teacher" -> teacherList.add(createTeacher(namePerson, agePerson, login, password));
//            default -> log.warn("Incorrect variable entered");
//        }
//    }
//
//    private void addStudentInDb(String namePerson, int agePerson, String login, String password) {
//        var sql = "insert into my.student (name, login, password, age) values (?, ?, ?, ?)";
//        try (Connection con = MyConnectionPool.getInstance().getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, namePerson);
//            ps.setString(2, login);
//            ps.setString(3, password);
//            ps.setInt(4, agePerson);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                studentDbList.add(
//                        new StudentDb().withName(namePerson)
//                                .withLogin(login)
//                                .withPassword(password)
//                                .withAge(agePerson));
//
//            }
//        } catch (MyWebAppException | SQLException e) {
//            myLogger.error("Connection error ", e);
//        }
//    }
//
//    private static class SingletonHelper {
//        private static final MyDbService myService = new MyDbService();
//    }
//}
