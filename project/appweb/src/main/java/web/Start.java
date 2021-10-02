package web;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/home")
public class Start extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // resp.setContentType("text/html");
       /// resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
       // writer.write("<p><span style='color: blac;'>Hello in my project</span></p>");
        out.println("<html>");
        out.println("<body>");
        out.println("<h1 align=center> The simplest servlet</h1>" );
        out.println("<p align=center><b> "  + "</b></p>");
        out.println("</body>");
        out.println("</body>");
    }
}
