package servlet;

import util.IinUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "IinController", urlPatterns = "/checkIin")
public class IinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter out = resp.getWriter();

        String iin = req.getParameter("iin");
        String date = req.getParameter("date");
        String gender = req.getParameter("gender");

        resp.setContentType("text/plain");

        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Boolean valid = IinUtils.iinCheck(iin, birthDate, gender);
        out.println(valid.toString());
        out.close();
    }
}
