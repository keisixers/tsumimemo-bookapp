package com.example.bookmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.model.User;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    // 書籍一覧の検索（部分一致）
    List<Book> findByTitleContainingIgnoreCaseOrAuthorsContainingIgnoreCase(String title, String authors);
    
    // 登録前の重複チェック用（完全一致）
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByUser(User user);
}
