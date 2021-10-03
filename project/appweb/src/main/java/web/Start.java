package web;

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

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1 align=center> Welcome in my SchoolProgramm </h1>");
        out.println("<h1 align=center><b> " + "There you can get some information about " +
                "student and teacher" + "</b></h1>");
        out.println("<p align=center><c> " + "created by Vasili Zasinets " +
                "</c></p>");
        out.println("</body>");
        out.println("</body>");
    }
}
