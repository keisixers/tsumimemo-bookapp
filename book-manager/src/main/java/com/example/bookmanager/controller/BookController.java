package com.example.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	@GetMapping("/book_list")
	public String showBookList(Model model) {
		String sampleTitle = "Java入門書";
		model.addAttribute("title", sampleTitle);
	return "book_list";
	}
}
