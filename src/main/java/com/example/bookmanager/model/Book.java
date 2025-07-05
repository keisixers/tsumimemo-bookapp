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
    private Long id;

    private String isbn;

    private String title;
    private String authors;
    private String publishedDate;
    private String thumbnailUrl;
    private Integer pageCount;
    private String printType;
    private String language;
    private String publisher;
    
    @Column(length = 2000)
    private String description;  // ← 追加

    public Book() {}

    public Book(String title, String authors, String publishedDate, String thumbnailUrl,
                Integer pageCount, String printType, String language, String publisher, String description) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.pageCount = pageCount;
        this.printType = printType;
        this.language = language;
        this.publisher = publisher;
        this.description = description;
    }

    // Getter
    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthors() { return authors; }
    public String getPublishedDate() { return publishedDate; }
    public String getThumbnailUrl() { return thumbnailUrl; }
    public Integer getPageCount() { return pageCount; }
    public String getPrintType() { return printType; }
    public String getLanguage() { return language; }
    public String getPublisher() { return publisher; }
    public String getDescription() { return description; }

    // Setter
    public void setId(Long id) { this.id = id; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthors(String authors) { this.authors = authors; }
    public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
    public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }
    public void setPrintType(String printType) { this.printType = printType; }
    public void setLanguage(String language) { this.language = language; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setDescription(String description) { this.description = description; }
}
