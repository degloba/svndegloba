package com.insacosa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;


@Entity
public class Book {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key id;

   private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
   private List<Chapter> chapters = new ArrayList<Chapter>();

	public Key getId() {
		return id;
	}

	public void setId(Key id) {	
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

   // getters and setters
    
    
}