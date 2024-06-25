package com.bookbroker.bookbroker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbroker.bookbroker.models.Book;
import com.bookbroker.bookbroker.models.User;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBorrowerIsNull();
    List<Book> findByBorrower(User user);
}
