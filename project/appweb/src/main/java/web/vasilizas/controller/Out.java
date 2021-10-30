package web.vasilizas.controller;

import web.vasilizas.controller.dataBase.MyConnectionPool;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class Out extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyConnectionPool.getInstance().getConnection().close();
            myLogger.info("Out session");
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect("/myweb/home");
        } catch (SQLException | IOException e) {
            myLogger.error(String.valueOf(e));
        }
    }
}
