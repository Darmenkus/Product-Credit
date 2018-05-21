package servlet;

import model.Client;
import model.CreditRequest;
import model.Period;
import util.Constants;
import util.CreditUtils;
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

@WebServlet(name = "CreditRequestController", urlPatterns = "/addCreditRequest")
public class CreditRequestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/login");
            return;
        }
        Connection connection = Utils.getStoredConnection(req);
        List<Period> periods = DBUtils.getPeriods(connection);
        req.setAttribute("periods", periods);
        req.getRequestDispatcher("/pages/addCreditRequest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer clientId = (Integer) req.getSession().getAttribute("clientId");

        Connection connection = Utils.getStoredConnection(req);

        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setClientId(clientId);
        creditRequest.setAmount(Double.valueOf(req.getParameter("amount")));

        Integer periodVal = Integer.valueOf(req.getParameter("period"));
        Period period = DBUtils.getPeriodByVal(connection, periodVal);
        creditRequest.setPeriod(period);

        creditRequest.setRate(req.getParameter("rate"));
        creditRequest.setMonthlyPayment(Double.valueOf(req.getParameter("monthlyPayment")));
        creditRequest.setAmountInUSD(Double.valueOf(req.getParameter("amountInUSD")));
        creditRequest.setTotalPayout(Double.valueOf(req.getParameter("totalPayout")));
        creditRequest.setOverpayment(Double.valueOf(req.getParameter("overpayment")));

        Client client = DBUtils.getClient(connection, clientId);
        List<CreditRequest> requests = DBUtils.getRequests(connection, clientId);

        if (CreditUtils.approveCredit(client, requests, creditRequest)) {
            creditRequest.setStatus(Constants.STATUS_APPROVED);
            session.setAttribute("resultMessage", Constants.SUCCESS);
        } else {
            creditRequest.setStatus(Constants.STATUS_REJECTED);
            session.setAttribute("resultMessage", Constants.FAILURE);
        }

        DBUtils.addRequest(connection, creditRequest);

        resp.sendRedirect("/result");
    }
}
