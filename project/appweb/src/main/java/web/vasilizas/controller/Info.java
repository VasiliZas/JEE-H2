package web.vasilizas.controller;

import web.vasilizas.repositories.DbTeacherRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.String.valueOf;
import static web.vasilizas.controller.authentication.Authentication.myLogger;

@WebServlet("/info2")
public class Info extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        myLogger.info("Info about teacher");
        HttpSession session = req.getSession();
        try {
            var list = new DbTeacherRepository().findAll();
            session.setAttribute("teacherInfo", list);
            myLogger.info("Go to page with all teacher info");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/info");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(valueOf(e));
        }
    }
}
