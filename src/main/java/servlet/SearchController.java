package servlet;

import util.DBUtils;
import util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "SearchController", urlPatterns = "/search", loadOnStartup = 1)
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/login");
            return;
        }
        req.getRequestDispatcher("/pages/search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String iin = req.getParameter("iin");

        Connection connection = Utils.getStoredConnection(req);
        Integer clientId = DBUtils.getClientId(connection, iin);

        if (clientId == null) {
            resp.sendRedirect("/addClient");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("clientId", clientId);
            resp.sendRedirect("/requestList");
        }
    }
}
