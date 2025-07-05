package com.example.bookmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.service.GoogleBooksService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	private final BookRepository bookRepository;
	private final GoogleBooksService googleBooksService;
	
	public BookController(BookRepository bookRepository,GoogleBooksService googleBooksService) {
		this.bookRepository = bookRepository;
		this.googleBooksService = googleBooksService;
	}
	
	@GetMapping("/register")
	public String showSearchResults(@RequestParam(required = false)String keyword, Model model) {
		if(keyword!=null && !keyword.isEmpty()) {
			List<Book>books = googleBooksService.searchBooks(keyword);
			model.addAttribute("books", books);
			model.addAttribute("keyword", keyword);
		}
		return "book_register";
	}
	
	@PostMapping("/register")
	public String registerBook(@ModelAttribute Book book, Model model) {
		if(bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
			model.addAttribute("errorMessage", "このISBNの書籍は既に登録されています。");
			model.addAttribute("books", List.of(book));
			return "book_register";
		}
		
		bookRepository.save(book);
		return "redirect:/books";
	}
	
	@GetMapping
	public String listBooks(Model model, @RequestParam(required = false)String keyword) {
		List<Book>books;
		if(keyword != null && !keyword.isEmpty()) {
			books =
			bookRepository.findByTitleContainingIgnoreCaseOrAuthorsContainingIgnoreCase(keyword, keyword);
		} else {
			books = bookRepository.findAll();
		}
		model.addAttribute("books", books);
		return "book_list";
	}
	
	@GetMapping("/{id}")
	public String showBookDetail(@PathVariable Long id, Model model) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("無効な書籍ID:" + id));
		model.addAttribute("book", book);
		return "book_detail";
	}
	
	@PostMapping("/{id}/delete")
	public String deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
		return "redirect:/books";
	}

}
