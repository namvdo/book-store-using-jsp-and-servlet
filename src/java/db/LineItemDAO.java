/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.LineItemBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class LineItemDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public LineItemDAO() {

        try {
            connection = DBContext.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            System.out.println("success");
        } catch (Exception e) {
            e.getCause();
            System.out.println("not success");
        }
    }

    public void insertLineItems(List<LineItemBean> items) throws SQLException {
        StringBuilder sql = new StringBuilder("insert into TransactionItem (isbn, transaction_id, quantity) values");
        for (LineItemBean item : items) {
            sql.append("('" + item.getIsbn() + "', '");
            sql.append(item.getTransactionId() + "', '");
            sql.append(item.getQuantity() + "'),");

        }

        sql.replace(sql.length() - 1, sql.length(), ";");
        statement.executeLargeUpdate(sql.toString());
    }

    public List<LineItemBean> getLineItemTransaction(String username, int transactionID) throws SQLException {
        List<LineItemBean> list = new ArrayList<>();
        String sql = "select TransactionItem.* from TransactionItem join Transactions on Transactions.transaction_id = TransactionItem.transaction_id join Book on Book.isbn = TransactionItem.isbn where Transactions.username='" + username + "'" + " and Transactions.transaction_id='" + transactionID + "'";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            LineItemBean item = new LineItemBean();
            item.setIsbn(rs.getString(1));
            item.setQuantity(rs.getInt(5));
            list.add(item);

        }
        return list;

    }
    // join multiple tables, book, transaction and line item to display a transaction item
    public List<String> getLineItemInfo(String username, int transactionID) throws SQLException {
        List<String> items = new ArrayList<>();
        
        String sql = "select b.cover_img, b.price, b.title, t.total_price, i.quantity from Book b inner join TransactionItem i on b.isbn = i.isbn inner join Transactions t on i.transaction_id = t.transaction_id where username= ?and t.transaction_id = ?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, username);
        pre.setInt(2, transactionID);
        rs = pre.executeQuery();
        while (rs.next()) {
            StringBuilder item = new StringBuilder();
            item.append(rs.getString(1) + " ");
            item.append(rs.getDouble(2) + " ");
            item.append(rs.getString(3) + " ");
            item.append(rs.getDouble(4) + " ");
            item.append(rs.getInt(5) + " ");
            items.add(item.toString());
        }
        return items;
    }

}
