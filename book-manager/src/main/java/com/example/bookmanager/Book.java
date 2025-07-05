package com.example.bookmanager;

public class Book {
	
	private String title;
	private String authors;
	private String thumbnaiUrl;
	
	public Book(String title, String authors, String thumbnaiUrl) {
		this.title = title;
		this.authors = authors;
		this.thumbnaiUrl = thumbnaiUrl;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public String getthumbnaiUrl() {
		return thumbnaiUrl;
	}

}
