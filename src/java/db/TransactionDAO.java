/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.TransactionBean;
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
public class TransactionDAO {
    
     private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    private static PreparedStatement pre;
    
    public TransactionDAO() {
         try {
            connection = DBContext.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            System.out.println("success");
        } catch (Exception e) {
            e.getCause();
            System.out.println("not success");
            
        } 
    }
    public int insertTransactionAndGetTransactionId(TransactionBean transaction) throws SQLException {
        int rsNo = 0;
        String sql = "insert into Transactions (username, purchase_date, total_price) "
							+ "values('"
					        + transaction.getUserName() + "', GETDATE(), '"
							+ transaction.getTotalPrice() + "'" + ")";
        statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        rs = statement.getGeneratedKeys();
        if (rs.next()){
            rsNo = rs.getInt(1);
        }
        return rsNo;
    }
    public List<TransactionBean> getDistinctTransactions(String username) throws SQLException {
        List<TransactionBean> list = new ArrayList<>();
        String sql = "select distinct T.* from Transactions T inner join Users U on U.username = T.username where U.username = ? order by T.transaction_id";
        pre = connection.prepareStatement(sql);
        pre.setString(1, username);
        rs = pre.executeQuery();
        while (rs.next()) {
            int tranId = rs.getInt(1);
            String date = rs.getString(3).split(" ")[0];
            double totalPrice = rs.getDouble(4);
            TransactionBean tran = new TransactionBean(tranId, username, date, totalPrice);
            list.add(tran);
        }
        
        return list;
    }
}
