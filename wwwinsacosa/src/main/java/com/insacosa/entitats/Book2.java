package com.insacosa.entitats;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.datanucleus.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;

@Entity
public class Book2 {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true")
    private String id;

   private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
   private List<String> chapters = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getChapters() {
		return chapters;
	}

	public void setChapters(List<String> chapters) {
		this.chapters = chapters;
	}

   // getters and setters
    
    
}