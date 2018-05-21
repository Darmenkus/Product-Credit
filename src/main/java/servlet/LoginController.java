package servlet;

import model.User;
import util.AuthUtils;
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

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login != null) {
            resp.sendRedirect("/search");
            return;
        }
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Connection connection = Utils.getStoredConnection(req);
        User user = DBUtils.getUser(connection, login);

        if (AuthUtils.authenticateUser(user, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            resp.sendRedirect("/search");
        } else {
            req.setAttribute("errorMessage", "Неверный логин или пароль");
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}
