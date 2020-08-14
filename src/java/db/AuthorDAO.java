/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.AuthorBean;
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
public class AuthorDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public AuthorDAO() {

        try {
            connection = DBContext.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            System.out.println("success");
        } catch (Exception e) {
            e.getCause();
            System.out.println("not success");
        }
    }

    public List<AuthorBean> searchAuthorsByIsbn(String isbn) throws SQLException {
        List<AuthorBean> authors = new ArrayList<>();
        // potentially returns more than one author, an author with an id matched with the id in the BookAuthor and has the isbn we are searching.
        String sql = "select author_name from Author join BookAuthor on Author.author_id = BookAuthor.author_id where BookAuthor.isbn='" + isbn + "'";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            AuthorBean author = new AuthorBean();
            author.setAuthorName(rs.getString(1));
            authors.add(author);
        }
        return authors;
    }

    

}
