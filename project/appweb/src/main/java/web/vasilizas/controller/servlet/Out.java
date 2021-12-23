package web.vasilizas.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Out extends HttpServlet {

    private final Logger myLogger = LoggerFactory.getLogger(Out.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            myLogger.info("Out session");
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect("/myweb/home");
        } catch (IOException e) {
            myLogger.error(String.valueOf(e));
        }
    }
}
