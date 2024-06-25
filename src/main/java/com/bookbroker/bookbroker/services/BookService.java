package com.bookbroker.bookbroker.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbroker.bookbroker.models.Book;
import com.bookbroker.bookbroker.models.User;
import com.bookbroker.bookbroker.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public List<Book> allBooks() {
        return bookRepo.findAll();
    }

    public List<Book> unborrowedBooks(User user) {
        return bookRepo.findByBorrowerIsNull();
    }

    public List<Book> borrowedBooks(User user) {
        return bookRepo.findByBorrower(user);
    }

    public Book findBook(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Book book) {
        bookRepo.delete(book);
    }

    public void addBorrower(Book book, User user) {
        book.setBorrower(user);
        bookRepo.save(book);
    }

    public void removeBorrower(Book book) {
        book.setBorrower(null);
        bookRepo.save(book);
    }
}
