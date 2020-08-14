/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Nam
 */
public class ReviewBean implements Serializable {
        private int rating;
	private String userName;
	private String isbn;
	private String reviewText;
	
	public ReviewBean() {
		this.rating = 0;
		this.userName = "";
		this.isbn = "";
		this.reviewText = "";
	}

    public ReviewBean(int rating, String userName, String isbn, String reviewText) {
        this.rating = rating;
        this.userName = userName;
        this.isbn = isbn;
        this.reviewText = reviewText;
    }

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
       
}
