package web.vasilizas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/about")
public class Start extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("<html>");
        out.write("<body>");
        out.write("<h1 align=center> Здесь должно быть описание сервиса </h1>");
        out.write("<h1 align=center><b> " + "Можно описать сервис, его создателей, цели  и т.п. " +
                "Также здесь можно поставить какую нибудь картинку. Например котиков. " +
                "Котиков любят практически все" + "</b></h1>");
        out.write(" <p center><c></c></p>");
        out.write(" <p center><c></c></p>");
        out.write(" <p center><c></c></p>");
        out.write(" <align=center>  <img src=img/03.jpg\n" +
                "     width=350px\n" +
                "     height=350px/>");
        out.write(" <p center><c></c></p>");
        out.write("<p align=center><c> " + "created by Vasili Zasinets " +
                "</c></p>");
        out.write(" <p center><c></c></p>");
        out.write("<button onclick=\"history.back()\">Go Back</button>");
        out.write("</body>");
        out.write("</body>");
    }
}
