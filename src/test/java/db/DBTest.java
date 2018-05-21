package db;

import model.User;
import org.junit.Assert;
import org.junit.Test;
import util.DBUtils;
import util.MySQLConnUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    @Test
    public void getUserTest() throws SQLException, ClassNotFoundException {
        Connection connection = MySQLConnUtils.getMySQLConnection();
        User user = DBUtils.getUser(connection, "user");

        Assert.assertEquals(Integer.valueOf(1), user.getId());
    }
}
