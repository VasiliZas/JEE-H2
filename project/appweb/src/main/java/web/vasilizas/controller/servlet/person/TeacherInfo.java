package web.vasilizas.controller.servlet.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.bean.db.TeacherDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static java.lang.String.valueOf;
import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

//@WebServlet("/info2")
public class TeacherInfo extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(TeacherInfo.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        myLogger.info("Info about teacher");
        HttpSession session = req.getSession();
        try {
            List<TeacherDb> list = getStrategyInstance().findAll();
            session.setAttribute("teacherInfo", list);
            myLogger.info("Go to page with all teacher info");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/info");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            myLogger.warn(valueOf(e));
        }
    }
}
