/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import db.ReviewDAO;
import db.AuthorDAO;
import db.GenreDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class BookBean implements Serializable {
    private int numOfView;

    public void setNumOfView(int numOfView) {
        this.numOfView = numOfView;
    }
    private String isbn;
    private String title;
    private String publisher;
    private double price;
    private String description;
    private String publishDate;
    private String coverImageFile;
    private int inventory;

    public BookBean(String isbn, String title, String publisher, double price,
            String description, String publishDate, String coverImageFile,
            int inventory) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.description = description;
        this.publishDate = publishDate;
        this.coverImageFile = coverImageFile;
        this.inventory = inventory;
    }

    public BookBean() {
        this.isbn = "";
        this.title = "";
        this.publisher = "";
        this.price = 0.0;
        this.description = "";
        this.publishDate = "";
        this.coverImageFile = "";
        this.inventory = 0;
        this.numOfView = 0;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCoverImageFile() {
        return coverImageFile;
    }

    public void setCoverImageFile(String coverImageFile) {
        this.coverImageFile = coverImageFile;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getAuthors() throws SQLException {
        String authorNames = "";

        List<AuthorBean> authors = new AuthorDAO().searchAuthorsByIsbn(this.isbn);
        authorNames = authors.get(0).getAuthorName();
        for (int i = 1; i < authors.size(); i++) {
            authorNames += ", " + authors.get(i).getAuthorName();
        }
        return authorNames;
    }

    public String getGenres() {
        String genreNames = "";

        List<GenreDAO> genres = new GenreDAO().searchForGenres(this.isbn);
        genreNames = genres.get(0).getGenreName();
        for (int i = 1; i < genres.size(); i++) {
            genreNames += "; " + genres.get(i).getGenreName();
        }
        return genreNames;
    }

    public int getNumOfView() {
        return numOfView;
    }

    public float getRating() throws SQLException {
        float score = 0;
        score = new ReviewDAO().getReviewScore(this.isbn);

        return score;
    }

   
}
