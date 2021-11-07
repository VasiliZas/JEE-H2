package web.vasilizas.controller.person;

import vasilizas.exception.MyWebAppException;
import web.vasilizas.repositories.factory.RepositoryFactory;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.String.valueOf;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/info2")
public class TeacherInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        myLogger.info("Info about teacher");
        HttpSession session = req.getSession();
        try {
            var list = RepositoryFactory.getTeacherRepository("JPA").findAll();
            session.setAttribute("teacherInfo", list);
            myLogger.info("Go to page with all teacher info");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/info");
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException | MyWebAppException | PersistenceException e) {
            myLogger.warn(valueOf(e));
        }
    }
}
