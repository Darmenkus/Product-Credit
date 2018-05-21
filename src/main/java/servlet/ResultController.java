package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ResultController", urlPatterns = "/result")
public class ResultController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/login");
            return;
        }
        String resultMessage = (String) session.getAttribute("resultMessage");
        req.setAttribute("resultMessage", resultMessage);
        req.getRequestDispatcher("/pages/result.jsp").forward(req, resp);
    }
}
