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
public class GenreBean implements Serializable {
    private String genreName;
	
	public GenreBean() {
		this.genreName = "";
	}

    public GenreBean(String genreName) {
        this.genreName = genreName;
    }

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
