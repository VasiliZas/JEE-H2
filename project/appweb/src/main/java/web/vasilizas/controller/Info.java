package web.vasilizas.controller;

import web.vasilizas.controller.authentication.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static vasilizas.myservice.person.MyService.createAndAddPerson;
import static vasilizas.myservice.person.MyService.log;
import static vasilizas.myservice.person.TeacherService.setTeacherSalary;
import static vasilizas.repository.TeacherRepository.teacherList;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/admin/info")
public class Info extends HttpServlet {
    @Override
    public void init() throws ServletException {
        createAndAddPerson("Teacher", "Jasmin", 22, "login", "password");
        createAndAddPerson("Teacher", "Zlata", 25, "login", "password");
        setTeacherSalary("Zlata", 6000);
        setTeacherSalary("Jasmin", 2000);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        myLogger.info("Info about teacher");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>Teacher info</title>");
        writer.write("<body align=center>");
        writer.write("<h1> Teacher Info </h1>");
        teacherList.forEach(teacher -> writer.write(teacher.toString() + "</p>"));
        writer.write("<p center><c></c></p>");
        writer.write("<a href=/myweb/home>Back to HOME</a>");
        writer.write("<p center>    <c></c>  </p>");
        writer.write("<a href=/myweb/admin>Back to work page</a>");
    }
}
