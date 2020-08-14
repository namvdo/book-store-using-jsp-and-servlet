/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import bean.BookBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Nam
 */
public class BookDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;
    private PreparedStatement pre;

    public BookDAO() {
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

    public BookBean getBookByISBN(String isbn) throws SQLException {
        System.out.println("isbn: " + isbn);
        String sql = "select * from Book where isbn='" + isbn + "'";
        rs = statement.executeQuery(sql);
        BookBean book = new BookBean();
        if (rs.next()) {
            String bookIsbn = rs.getString(1);
            String title = rs.getString(2);
            String publisher = rs.getString(3);
            double price = rs.getDouble(4);
            String description = rs.getString(5);
            String date = rs.getString(6);
            String img = rs.getString(7);
            int inventory = rs.getInt(8);
            book = new BookBean(bookIsbn, title, publisher, price, description, date, img, inventory);
            return book;

        } else {
            return null;
        }
    }

    public List<BookBean> selectAllBooks() throws SQLException {
        List<BookBean> listBooks = new ArrayList<>();
        String sql = "select * from Book";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String isbn = rs.getString(1);
            String title = rs.getString(2);
            String publisher = rs.getString(3);
            double price = rs.getDouble(4);
            String description = rs.getString(5);
            Date publicationDate = rs.getDate(6);
            String img = rs.getString(7);
            int inventory = rs.getInt(8);
            
            // format date
            String datePattern = "mm-dd-yyyy";
            DateFormat df = new SimpleDateFormat(datePattern);
            String dateFormated = df.format(publicationDate);
            BookBean book = new BookBean(isbn, title, publisher, price, description, dateFormated, img, inventory);
            listBooks.add(book);
        }
        
        return listBooks;

    }
    
    public List<BookBean> selectAllBookWithViews() throws SQLException{
                List<BookBean> listBooks = new ArrayList<>();

        String sql = "select Book.*, numView from Book join BookView on Book.isbn = BookView.isbn";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String isbn = rs.getString(1);
            String title = rs.getString(2);
            String publisher = rs.getString(3);
            double price = rs.getDouble(4);
            String description = rs.getString(5);
            Date publicationDate = rs.getDate(6);
            String img = rs.getString(7);
            int inventory = rs.getInt(8);
            int numViews = rs.getInt(9);
            // format date
            String datePattern = "mm-dd-yyyy";
            DateFormat df = new SimpleDateFormat(datePattern);
            String dateFormated = df.format(publicationDate);
            BookBean book = new BookBean(isbn, title, publisher, price, description, dateFormated, img, inventory);
            book.setNumOfView(numViews);
            listBooks.add(book);
        }
        
        return listBooks;
    }

    // update book quantity to the database
    public int updateBookQuantityToDB(BookBean book) throws SQLException {
        String sql = "update Book set inventory='" + book.getInventory() + "'" + " where Book.isbn='" + book.getIsbn() + "'";
        return statement.executeUpdate(sql);

    }

    public List<BookBean> searchBookByKeyword(String input) throws SQLException {
        List<BookBean> listBook = new ArrayList<>();
        String sql = "select Book.* from Book inner join BookAuthor on Book.isbn = BookAuthor.isbn inner join Author on BookAuthor.author_id = Author.author_id inner join BookGenre on Book.isbn = BookGenre.isbn inner join Genre on BookGenre.genre_name = Genre.genre_name \n"
                + "where Book.title like '%" + input + "%' or Author.author_name like '%" + input + "' or Genre.genre_name like '%" + input + "%' or Book.isbn like '%" + input + "%'or Book.description like '%" + input + "%'";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String isbn = rs.getString(1);
            String title = rs.getString(2);
            String publisher = rs.getString(3);
            double price = rs.getDouble(4);
            String description = rs.getString(5);
            Date publicationDate = rs.getDate(6);
            String img = rs.getString(7);
            int inventory = rs.getInt(8);
            String datePattern = "mm-dd-yyyy";
            DateFormat df = new SimpleDateFormat(datePattern);
            String dateFormated = df.format(publicationDate);
            BookBean book = new BookBean(isbn, title, publisher, price, description, dateFormated, img, inventory);
            listBook.add(book);
        }
        return listBook;
    }
    public List<BookBean> sortAllBookByPrice(boolean lowest) throws SQLException {
        List<BookBean> books = selectAllBooks();
        if (lowest) {
            books = books.stream().sorted(Comparator.comparingDouble(BookBean::getPrice)).collect(Collectors.toList());
        } else {
            Comparator<BookBean> reversedCompare = new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    BookBean book1 = (BookBean) o1;
                    BookBean book2 = (BookBean) o2;
                    if (book1.getPrice() < book2.getPrice()) {
                        return 1;
                    } else if (book1.getPrice() > book2.getPrice()){
                        return -1;
                    } 
                    return 0;
                }
                
            };
            books = books.stream().sorted(reversedCompare).collect(Collectors.toList());
        }
        return books;
    }
    
     public List<BookBean> sortAllBookByView() throws SQLException {
        List<BookBean> books = selectAllBookWithViews();
       
            Comparator<BookBean> reversedCompare = new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    BookBean book1 = (BookBean) o1;
                    BookBean book2 = (BookBean) o2;
                    if (book1.getNumOfView()< book2.getNumOfView()) {
                        return 1;
                    } else if (book1.getNumOfView()> book2.getNumOfView()){
                        return -1;
                    } 
                    return 0;
                }
                
            };
            books = books.stream().sorted(reversedCompare).collect(Collectors.toList());
        books.stream().forEach(book ->System.out.println("from book DAO" + book.getNumOfView()));
        return books;
    }

     
     
     // book views operations
     
     public int getNumViewsFromBook(String isbn) throws SQLException{
        String sql = "select * from BookView where isbn='" + isbn + "'";
        rs = statement.executeQuery(sql);
        if (rs.next()) return rs.getInt(2);
        return 0;
    }
    public void addNewViewToBook(String isbn) throws SQLException {
        String sql = "insert into BookView(isbn, numView) values ('" + isbn + "', " + 1 + ")";
        statement.execute(sql);
        
    }
    // +1 view and return this
    public void updateBookView(String isbn) throws SQLException {
        int num = getNumViewsFromBook(isbn) + 1;
//        String sql = "insert into BookView(isbn, numView) values ('" + isbn + "', " + num + ")";
        String sql = "update BookView set numView ='" + num + "' where isbn='" + isbn + "'"; 
        
        statement.executeUpdate(sql);
        
    };
    
    public boolean checkBookViewExist(String isbn) throws SQLException {
        String sql = "select * from BookView where isbn=?";
        pre = connection.prepareStatement(sql);
        pre.setString(1, isbn);
        rs = pre.executeQuery();
        return rs.next();
    }
    public int getNumViewFromISBN(String isbn) throws SQLException {
       String sql = "select * from BookView where isbn=?";
       pre = connection.prepareStatement(sql);
       pre.setString(1, isbn);
       rs = pre.executeQuery();
        
        System.out.println("bookdao " + isbn);
        int numViews = 0;
        
        if (rs.next()) {
            numViews = rs.getInt(2);
        }
        
        System.out.println("numView:" + numViews);
        return numViews;
    }
}
