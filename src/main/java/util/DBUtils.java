package util;

import model.Client;
import model.CreditRequest;
import model.Period;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static void addUser(Connection connection, User user) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("insert into users(login, password, salt) values (?, ?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(Connection connection, String login) {
        User user = null;
        try {
            PreparedStatement statement = connection.
                    prepareStatement("select * from users where login = ?");
            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void addClient(Connection connection, Client client) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("insert into clients(iin, last_name, first_name, middle_name, " +
                        "phone_number, birth_date, gender, " +
                        "document_number, document_issued_by, document_date_of_issue, document_valid_to, " +
                        "salary, payments) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, client.getIin());
        statement.setString(2, client.getLastName());
        statement.setString(3, client.getFirstName());
        statement.setString(4, client.getMiddleName());
        statement.setString(5, client.getPhoneNumber());
        statement.setDate(6, getSQLDate(client.getBirthDate()));
        statement.setString(7, client.getGender());
        statement.setString(8, client.getDocumentNumber());
        statement.setString(9, client.getDocumentIssuedBy());
        statement.setDate(10, getSQLDate(client.getDocumentDateOfIssue()));
        statement.setDate(11, getSQLDate(client.getDocumentValidTo()));
        statement.setDouble(12, client.getSalary());
        statement.setDouble(13, client.getPayments());
        statement.executeUpdate();
    }

    public static Integer getClientId(Connection connection, String iin) {
        Integer id = null;
        try {
            PreparedStatement statement = connection.
                    prepareStatement("select id from clients where iin = ?");
            statement.setString(1, iin);
            statement.setMaxRows(1);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static Client getClient(Connection connection, Integer id) {
        Client client = null;
        try {
            PreparedStatement statement = connection.
                    prepareStatement("select * from clients where id = ?");
            statement.setInt(1, id);
            statement.setMaxRows(1);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setIin(rs.getString("iin"));
                client.setLastName(rs.getString("last_name"));
                client.setFirstName(rs.getString("first_name"));
                client.setMiddleName(rs.getString("middle_name"));
                client.setPhoneNumber(rs.getString("phone_number"));
                client.setBirthDate(rs.getDate("birth_date"));
                client.setGender(rs.getString("gender"));
                client.setDocumentNumber(rs.getString("document_number"));
                client.setDocumentIssuedBy(rs.getString("document_issued_by"));
                client.setDocumentDateOfIssue(rs.getDate("document_date_of_issue"));
                client.setDocumentValidTo(rs.getDate("document_valid_to"));
                client.setSalary(rs.getDouble("salary"));
                client.setPayments(rs.getDouble("payments"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    public static void addRequest(Connection connection, CreditRequest creditRequest) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("insert into credit_requests(client_id, " +
                            "amount, period_id, rate, " +
                            "monthly_payment, amount_in_usd, total_payout, overpayment, status) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, creditRequest.getClientId());
            statement.setDouble(2, creditRequest.getAmount());
            statement.setLong(3, creditRequest.getPeriod().getId());
            statement.setString(4, creditRequest.getRate());
            statement.setDouble(5, creditRequest.getMonthlyPayment());
            statement.setDouble(6, creditRequest.getAmountInUSD());
            statement.setDouble(7, creditRequest.getTotalPayout());
            statement.setDouble(8, creditRequest.getOverpayment());
            statement.setString(9, creditRequest.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<CreditRequest> getRequests(Connection connection, Integer clientId) {
        List<CreditRequest> creditRequests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from credit_requests where client_id = ?");
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CreditRequest creditRequest = new CreditRequest();
                creditRequest.setId(rs.getInt("id"));
                creditRequest.setClientId(rs.getInt("client_id"));
                creditRequest.setRequestDate(rs.getDate("request_date"));
                creditRequest.setRequestNumber(rs.getString("request_number"));
                creditRequest.setAmount(rs.getDouble("amount"));
                creditRequest.setPeriod(getPeriodById(connection, rs.getInt("period_id")));
                creditRequest.setRate(rs.getString("rate"));
                creditRequest.setMonthlyPayment(rs.getDouble("monthly_payment"));
                creditRequest.setAmountInUSD(rs.getDouble("amount_in_usd"));
                creditRequest.setTotalPayout(rs.getDouble("total_payout"));
                creditRequest.setOverpayment(rs.getDouble("overpayment"));
                creditRequest.setStatus(rs.getString("status"));
                creditRequests.add(creditRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creditRequests;
    }

    public static List<Period> getPeriods(Connection connection) {
        List<Period> periods = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from credit_periods");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Period period = new Period();
                period.setId(rs.getInt("id"));
                period.setPeriod(rs.getInt("period"));
                periods.add(period);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return periods;
    }

    public static Period getPeriodById(Connection connection, Integer id) {
        Period period = null;
        try {
            PreparedStatement statement = connection.
                    prepareStatement("select * from credit_periods where id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                period = new Period();
                period.setId(rs.getInt("id"));
                period.setPeriod(rs.getInt("period"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return period;
    }

    public static Period getPeriodByVal(Connection connection, Integer periodVal) {
        Period period = null;
        try {
            PreparedStatement statement = connection.
                    prepareStatement("select * from credit_periods where period = ?");
            statement.setInt(1, periodVal);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                period = new Period();
                period.setId(rs.getInt("id"));
                period.setPeriod(rs.getInt("period"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return period;
    }

    private static Date getSQLDate(java.util.Date date) {
        return new Date(date.getTime());
    }
}
