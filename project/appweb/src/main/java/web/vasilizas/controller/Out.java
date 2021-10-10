package web.vasilizas.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static web.vasilizas.controller.authentication.Authentication.myLogger;

public class Out extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        myLogger.info("Out session");
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("/myweb/home");
    }
}
