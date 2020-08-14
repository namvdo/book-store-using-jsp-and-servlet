/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.ReviewBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nam
 */
public class ReviewDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public ReviewDAO() {

        try {
            connection = DBContext.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            System.out.println("success");
        } catch (Exception e) {
            e.getCause();
            System.out.println("not success");
        }
    }
    // returns true if this review already inserted, false if not
    public boolean insertReview(ReviewBean review) throws SQLException {
        PreparedStatement pre;
        String tempSQL = "select * from Rating where isbn = ? and username =?";
        pre = connection.prepareStatement(tempSQL);
        pre.setString(1, review.getISBN());
        pre.setString(2, review.getUserName());

        rs = pre.executeQuery();
        if (rs.next()) {
            return true;
        }
        String sql = "insert into Rating(username, isbn, rating, review_text) values (?, ?, ?, ?)";
        connection.setAutoCommit(false);
        pre = connection.prepareStatement(sql);
        pre.setString(1, review.getUserName());
        pre.setString(2, review.getISBN());
        pre.setInt(3, review.getRating());
        pre.setString(4, review.getReviewText());
        pre.execute();
        connection.commit();
        pre.close();
        connection.close();
        return false;

    }

    public float getReviewScore(String isbn) throws SQLException {
        String sql = "select count(isbn) as count, sum(rating) from Rating where isbn=?";
        connection.setAutoCommit(false);
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, isbn);
        rs = pre.executeQuery();

        float average = 0;
        if (rs.next()) {
            int count = rs.getInt(1);
            int sum = rs.getInt(2);
            if (count == 0) {
                return 0;
            }
            average = (float) sum / (float) count;
        }
        return average;
    }

    public List<ReviewBean> getAllReviewsFromIsbn(String isbn) throws SQLException {
        List<ReviewBean> list = new ArrayList<>();
        String sql = "select * from Rating where isbn =?";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1, isbn);
        rs = pre.executeQuery();
        while (rs.next()) {
            String username = rs.getString(1);
            String bookId = rs.getString(2);
            int rating = rs.getInt(3);
            String review_text = rs.getString(4);
            list.add(new ReviewBean(rating, username, bookId, review_text));
        }
        return list;

    }
    public List<ReviewBean> selectReviewsFromUser(String username) throws SQLException {
        List<ReviewBean> reviews = new ArrayList<>();
        String sql = "select * from Rating where username='" + username + "'";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String isbn = rs.getString(2);
            int rating = rs.getInt(3);
            String review_text = rs.getString(4);
            reviews.add(new ReviewBean(rating, username, isbn, review_text));
        }
        return reviews;
    }
    
    public Map<Integer, Integer> getAllRatingAndRatingCount(String isbn) throws SQLException {
         Map<Integer, Integer> stat = new HashMap<>();
         String sql = "select rating, count(rating) as ratingCount from Rating where isbn ='" + isbn + "'" + "group by rating";
         rs = statement.executeQuery(sql);
         while (rs.next()) {
             int ratingStar = rs.getInt(1);
             int ratingCount = rs.getInt(2);
             stat.put(ratingStar, ratingCount);
         }
         System.out.println("OKKOOKOKKOKOKOK");
         for(Map.Entry<Integer,Integer> entry: stat.entrySet()) {
             System.out.println(entry.getKey() + " " + entry.getValue());
         }
         return stat;
    }
    
}
