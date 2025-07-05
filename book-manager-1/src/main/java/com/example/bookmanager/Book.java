package com.example.bookmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	
	private String title;
	private String authors;
	private String thumbnailUrl;
	
	public Book(String title, String authors, String thumbnailUrl) {
		this.title = title;
		this.authors = authors;
		this.thumbnailUrl = thumbnailUrl;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	
	public long setId() {
		return id;
	}
	
	public String setTitle() {
		return title;
	}
	
	public String setAuthors() {
		return authors;
	}
	
	public String setThumbnailUrl() {
		return thumbnailUrl;
	}

}
