package com.example.bookmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String isbn;
	
	private String title;
	private String authors;
	private String publisher;
	private String publishedDate;
	private String thumbnailUrl;
	private Integer pageCount;
	private String printType;
	private String language;
	
	@Column(length = 2000)
	private String description;
	
	public Book() {}
	
	public Book(String title, String authors, String publisher, String publishedDate, 
			    String thumbnailUrl, Integer pageCount, String printType, String language,
			    String description) {
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.thumbnailUrl = thumbnailUrl;
		this.pageCount = pageCount;
		this.printType = printType;
		this.language = language;
		this.description = description;
		
	}
	
	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthors() { return authors; }
	public String getPublisher() { return publisher; }
	public String getPublishedDate() { return publishedDate; }
	public String getThumbnailUrl() { return thumbnailUrl; }
	public Integer getPageCount() { return pageCount; }
	public String getPrintType() { return printType; }
	public String getLanguage() { return language; }
	public String getIsbn() { return isbn; }
	public String getDescription() { return description; }
	
	public void setId(Long id) { this.id = id; }
	public void setTitle(String title) { this.title = title; }
	public void setAuthors(String authors) { this.authors = authors; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }
	public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
	public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }
	public void setPrintType(String printType) { this.printType = printType; }
	public void setLanguage(String language) { this.language = language; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	public void setDescription(String description) { this.description = description; }

}
