package com.example.bookmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookmanager.Book;

@Controller
public class BookController {
	@GetMapping("/book_list")
	public String showBookList(Model model) {
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("Java入門書", "山田太郎", "https://example.com/java.jpg"));
		bookList.add(new Book("Spring Boot実践", "佐藤花子", "https://example.com/spring.jpg"));
		bookList.add(new Book("web開発の基礎", "田中一郎", "https://example.com/web.jpg"));
		
		model.addAttribute("bookList", bookList);
		
	return "book_list";
	}
	
	@GetMapping("book_form")
	public String showBookForm() {
		return "book_form";
	}
}
