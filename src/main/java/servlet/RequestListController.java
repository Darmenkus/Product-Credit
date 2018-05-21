package servlet;

import model.CreditRequest;
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
import java.util.List;

@WebServlet(name = "RequestListController", urlPatterns = "/requestList")
public class RequestListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/login");
            return;
        }

        Integer clientId = (Integer) req.getSession().getAttribute("clientId");
        Connection connection = Utils.getStoredConnection(req);
        List<CreditRequest> creditRequests = DBUtils.getRequests(connection, clientId);

        req.setAttribute("creditRequests", creditRequests);
        req.getRequestDispatcher("/pages/creditRequestList.jsp").forward(req, resp);
    }
}
