/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Nam
 */
public class AuthorBean implements Serializable{

    private int authorId;
    private String authorName;
    private LocalDate authorDob;
    
    public AuthorBean() {
        this.authorId = 0;
        this.authorName = "";
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getAuthorDob() {
        return authorDob;
    }

    public void setAuthorDob(LocalDate authorDob) {
        this.authorDob = authorDob;
    }

}
