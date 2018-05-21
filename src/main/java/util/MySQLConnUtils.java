package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {

        String hostName = "localhost";
        String dbName = "task_db";
        String userName = "user";
        String password = "pass";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useLegacyDatetimeCode=false&serverTimezone=UTC";

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
