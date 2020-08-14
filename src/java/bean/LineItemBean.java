/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.SQLException;
import db.BookDAO;
import java.io.Serializable;

/**
 * one book in a transaction, card with quantity
 *
 * @author Nam
 */
public class LineItemBean implements Serializable{

    private int transactionId;
    private int lineItemId;
    private String isbn;
    private String title;
    private int quantity;
    private double price;
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(int lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LineItemBean(int transactionId, int lineItemId, String isbn, String title, int quantity) {
        this.transactionId = transactionId;
        this.lineItemId = lineItemId;
        this.isbn = isbn;
        this.title = title;
        this.quantity = quantity;
    }

    public LineItemBean() {
        this.lineItemId = 0;
        this.transactionId = 0;
        this.isbn = "";
        this.quantity = 0;
        this.title = "";
    }
    
    public double getPrice() throws SQLException {
        double price = new BookDAO().getBookByISBN(isbn).getPrice();
        return price;
    }
    
    public double getTotalPrice() throws SQLException {
        return new BookDAO().getBookByISBN(isbn).getPrice() * this.quantity;
    }
    
    public boolean isEnougInventory() throws SQLException {
        return new BookDAO().getBookByISBN(this.isbn).getInventory() >= this.quantity;
    }
    
    // update book quantity of the book object and also database.
    public void updateBookQuantity() throws SQLException {
        BookDAO bookDAO = new BookDAO();
        BookBean book = bookDAO.getBookByISBN(isbn);
        book.setInventory(book.getInventory() - quantity);
        
        bookDAO.updateBookQuantityToDB(book);
        
    }
}
