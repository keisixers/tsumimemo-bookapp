

package com.example.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
