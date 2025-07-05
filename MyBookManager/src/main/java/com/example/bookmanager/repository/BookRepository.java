package com.example.bookmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanager.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book>findByTitleContainingIgnoreCaseOrAuthorsContainingIgnoreCase (String title, String authors);
	
	Optional<Book>findByIsbn(String isbn);

}
