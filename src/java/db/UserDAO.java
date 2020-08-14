/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nam
 */
public class UserDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public UserDAO() {
        initConnection();
    }

    public boolean registerUser(UserBean user) throws SQLException {
        int resultNo = 0;
        // handles when there is an existing username
        String selectUser = "select username from Users where username=" + "'" + user.getUsername() + "'";
        rs = statement.executeQuery(selectUser);
        if (rs.next()) {
            return false;
        }
        String sql = "Insert into Users values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, user.getUsername());
        pre.setString(2, user.getPassword());
        pre.setString(3, user.getFirstName());
        pre.setString(4, user.getLastName());
        pre.setInt(5, user.getPhone());
        pre.setString(6, user.getEmail());
        pre.setBoolean(7, user.isStaff());

        resultNo = pre.executeUpdate();

        return resultNo == 1;

    }

    public UserBean getUserByUserName(String username) throws SQLException {
        String sql = "select * from Users where username=" + "'" + username + "'";
        rs = statement.executeQuery(sql);
        UserBean user = new UserBean();
        if (rs.next()) {
            user.setUsername(rs.getString(1));
            user.setFirstName(rs.getString(3));
            user.setLastName(rs.getString(4));
            user.setPhone(rs.getInt(5));
            user.setEmail(rs.getString(6));
        }
        return user;
    }

    // 0 - log in fail 1 - log in as normal user 2 - log in as an admin
    public int logUserIn(String username, String password) throws SQLException {
        String sql = "select * from Users where username='" + username + "' and password='" + password + "'";
        rs = statement.executeQuery(sql);
        boolean queryRs = rs.next();
        if (!queryRs) {
            return 0;
        }
        boolean role = rs.getBoolean(7);
        if (queryRs && role) {
            return 2;
        } else {
            return 1;
        }
    }

    private static void initConnection() {
        try {
            connection = DBContext.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            System.out.println("success");
        } catch (Exception e) {
            e.getCause();
            System.out.println("not success");
        }
    }

    public void updateUser(UserBean user) throws SQLException {

        String strQuery = "update users set "
                + "firstname='" + user.getFirstName()
                + "', lastname='" + user.getLastName()
                + "', email='" + user.getEmail()
                + "', phone='" + user.getPhone()
                + "' where username='" + user.getUsername() + "'";
        statement.executeUpdate(strQuery);
    }
}
