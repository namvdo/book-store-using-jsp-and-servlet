/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nam
 */
public class BookViewDAO {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public BookViewDAO() {
        initConnection();
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
    
}
