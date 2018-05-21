package servlet;

import model.Client;
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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ClientController", urlPatterns = "/addClient")
public class ClientController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/login");
            return;
        }
        req.getRequestDispatcher("/pages/addClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Client client = new Client();
        client.setIin(req.getParameter("iin"));
        client.setLastName(req.getParameter("lastName"));
        client.setFirstName(req.getParameter("firstName"));
        client.setMiddleName(req.getParameter("middleName"));
        client.setPhoneNumber(req.getParameter("phoneNumber"));

        Date birthDate = null;
        try {
            birthDate = dateFormat.parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setBirthDate(birthDate);

        client.setGender(req.getParameter("gender"));
        client.setDocumentNumber(req.getParameter("documentNumber"));
        client.setDocumentIssuedBy(req.getParameter("documentIssuedBy"));

        Date documentDateOfIssue = null;
        try {
            documentDateOfIssue = dateFormat.parse(req.getParameter("documentDateOfIssue"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setDocumentDateOfIssue(documentDateOfIssue);

        Date documentValidTo = null;
        try {
            documentValidTo = dateFormat.parse(req.getParameter("documentValidTo"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setDocumentValidTo(documentValidTo);

        client.setSalary(Double.valueOf(req.getParameter("salary")));
        client.setPayments(Double.valueOf(req.getParameter("payments")));

        Connection connection = Utils.getStoredConnection(req);
        try {
            DBUtils.addClient(connection, client);

            Integer clientId = DBUtils.getClientId(connection, client.getIin());

            HttpSession session = req.getSession();
            session.setAttribute("clientId", clientId);
            resp.sendRedirect("/addCreditRequest");
        } catch (SQLIntegrityConstraintViolationException e) {
            req.setAttribute("iinErrorMessage", "Клиент с таким ИИН уже существует");
            req.getRequestDispatcher("/pages/addClient.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
