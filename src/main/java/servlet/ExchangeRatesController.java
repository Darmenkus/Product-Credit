package servlet;

import util.ExchangeRatesUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ExchangeRatesController", urlPatterns = "/getRatesXML")
public class ExchangeRatesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dateFormat.format(new Date());
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/xml");
        out.println(ExchangeRatesUtils.getRatesXML(today));
        out.close();
    }
}
