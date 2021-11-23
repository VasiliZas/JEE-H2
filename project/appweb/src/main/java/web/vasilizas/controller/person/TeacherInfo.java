package web.vasilizas.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vasilizas.exception.MyWebAppException;

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
import static web.vasilizas.repositories.jpa.JpaTeacherRepository.getInstance;
import static web.vasilizas.repositories.strategy.TeacherRepositoryStrategy.getStrategyInstance;

@WebServlet("/info2")
public class TeacherInfo extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(TeacherInfo.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        myLogger.info("Info about teacher");
        HttpSession session = req.getSession();
        try {
            var list = getStrategyInstance().setTeacherRepository(getInstance()).findAll();
            session.setAttribute("teacherInfo", list);
            myLogger.info("Go to page with all teacher info");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin/info");
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException | MyWebAppException | PersistenceException e) {
            myLogger.warn(valueOf(e));
        }
    }
}
