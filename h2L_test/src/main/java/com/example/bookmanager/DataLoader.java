package com.example.bookmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner load(BookRepository repo) {
        return args -> {
            repo.save(new Book(null, "Spring入門", "佐藤"));
            repo.save(new Book(null, "Javaの基本", "山田"));
        };
    }
}
