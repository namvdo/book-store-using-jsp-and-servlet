/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author Nam
 */
public class TransactionBean implements Serializable {

    private int transactionId;
    private String username;
    private String purchaseDate;
    private double totalPrice;
    private int isProcessed;

    public TransactionBean(int transactionId, String username, String purchaseDate, double totalPrice) {
        this.transactionId = transactionId;
        this.username = username;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }

    public TransactionBean() {
        this.transactionId = 0;
        this.username = "";
        this.purchaseDate = "";
        this.totalPrice = 0.0;
    }

    // SET funcs ------------------------------------------------------//
    public void setTransactionId(int transaction_id) {
        this.transactionId = transaction_id;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPurchaseDate(String purchase_date) {
        this.purchaseDate = purchase_date;
    }

    public void setTotalPrice(double total_price) {
        this.totalPrice = total_price;
    }

    public void setIsProcessed(int isProcessed) {
        this.isProcessed = isProcessed;
    }

    // GET funcs ------------------------------------------------------//
    public int getTransactionId() {
        return transactionId;
    }

    public String getUserName() {
        return username;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getIsProcessed() {
        return isProcessed;
    }
}
