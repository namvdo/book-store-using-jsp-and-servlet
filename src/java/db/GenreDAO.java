/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.GenreBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class GenreDAO {

      private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public GenreDAO() {

        try {
            connection = DBContext.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            System.out.println("success");
        } catch (Exception e) {
            e.getCause();
            System.out.println("not success");
        }
    }
    
    public List<GenreDAO> searchForGenres(String bn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getGenreName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<GenreBean> getAllGenrenames () throws SQLException {
        
        String sql = "select genre_name from Genre";
        List<GenreBean> list = new ArrayList<>();
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new GenreBean(rs.getString(1)));
        }
      
        return list;
    }
}
