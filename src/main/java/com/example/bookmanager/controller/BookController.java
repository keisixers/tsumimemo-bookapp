package com.example.bookmanager.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.model.User;
import com.example.bookmanager.repository.BookRepository;
import com.example.bookmanager.repository.UserRepository;
import com.example.bookmanager.service.GoogleBooksService;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final GoogleBooksService googleBooksService;
    private final UserRepository userRepository;

    public BookController(BookRepository bookRepository, UserRepository userRepository,
    		GoogleBooksService googleBooksService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.googleBooksService = googleBooksService;
    }

    // 書籍登録画面（検索付き）
    @GetMapping("/register")
    public String showSearchResults(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            List<Book> books = googleBooksService.searchBooks(keyword);
            model.addAttribute("books", books);
            model.addAttribute("keyword", keyword);
        }
        return "book_register";
    }

    // 書籍登録処理
    @PostMapping("/register")
    public String registerBook(@ModelAttribute Book book, Model model) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            model.addAttribute("errorMessage", "このISBNの書籍はすでに登録されています。");
            model.addAttribute("books", List.of(book));
            return "book_register";
        }

        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            model.addAttribute("errorMessage", "ISBNが取得できない書籍は登録できません。");
            model.addAttribute("books", List.of(book));
            return "book_register";
        }

        // ここからログインユーザーを取得して、Bookに紐づける処理
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
        		.orElseThrow(() -> new RuntimeException("ログインユーザーが見つかりません"));

        book.setUser(user); // 書籍にユーザー情報を紐づける

        bookRepository.save(book);
        return "redirect:/books";
    }



    // 書籍一覧表示
    @GetMapping
    public String listBooks(Model model, @RequestParam(required = false) String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        List<Book> books;
        if (keyword != null && !keyword.isEmpty()) {
            books = bookRepository.findByUser(user).stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                    book.getAuthors().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        } else {
            books = bookRepository.findByUser(user);
        }

        model.addAttribute("books", books);
        return "book_list";
    }


    // 書籍詳細表示
    @GetMapping("/{id}")
    public String showBookDetail(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            model.addAttribute("errorMessage", "指定された書籍IDは存在しません。");
            return "book_detail";
        }

        model.addAttribute("book", book);
        return "book_detail";
    }

    // 書籍削除処理
    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
