package web.vasilizas.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static vasilizas.myservice.person.MyService.log;

@WebServlet("/out")
public class Out extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("Out session");
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("/myweb/index.html");
    }
}
