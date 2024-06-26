package com.bookbroker.bookbroker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookbroker.bookbroker.models.Book;
import com.bookbroker.bookbroker.models.LoginUser;
import com.bookbroker.bookbroker.models.User;
import com.bookbroker.bookbroker.services.BookService;
import com.bookbroker.bookbroker.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	   @Autowired
	    private UserService userServ;

	    @Autowired
	    private BookService bookServ;

	    @GetMapping("/")
	    public String index(Model model) {
	        model.addAttribute("newUser", new User());
	        model.addAttribute("newLogin", new LoginUser());
	        return "index.jsp";
	    }

	    @PostMapping("/register")
	    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
	        User user = userServ.register(newUser, result);
	        if(result.hasErrors()) {
	            model.addAttribute("newLogin", new LoginUser());
	            return "index.jsp";
	        }
	        session.setAttribute("userId", user.getId());
	        return "redirect:/bookmarket";
	    }

	    @PostMapping("/login")
	    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
	        User user = userServ.login(newLogin, result);
	        if(result.hasErrors() || user == null) {
	            model.addAttribute("newUser", new User());
	            return "index.jsp";
	        }
	        session.setAttribute("userId", user.getId());
	        return "redirect:/bookmarket";
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.setAttribute("userId", null);
	        return "redirect:/";
	    }

	    @GetMapping("/books")
	    public String showBooks(HttpSession session, Model model) {
	        if(session.getAttribute("userId") == null) {
	            return "redirect:/logout";
	        }
	        List<Book> books = bookServ.allBooks();
	        model.addAttribute("books", books);
	        return "books.jsp";
	    }

	    @GetMapping("/books/{id}")
	    public String showBook(@PathVariable("id") Long id, HttpSession session, Model model) {
	        Long userId = (Long) session.getAttribute("userId");
	        if(userId == null) {
	            return "redirect:/logout";
	        }
	        model.addAttribute("user", userServ.findById(userId));
	        Book book = bookServ.findBook(id);
	        model.addAttribute("book", book);
	        return "show_book.jsp";
	    }

	    @GetMapping("/books/edit/{id}")
	    public String edit(@PathVariable("id") Long id, HttpSession session, Model model) {
	        if(session.getAttribute("userId") == null) {
	            return "redirect:/logout";
	        }
	        Book book = bookServ.findBook(id);
	        model.addAttribute("book", book);
	        return "edit_book.jsp";
	    }

	    @PutMapping("/books/edit/{id}")
	    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
	        if(session.getAttribute("userId") == null) {
	            return "redirect:/logout";
	        }
	        if(result.hasErrors()) {
	            return "edit_book.jsp";
	        }
	        bookServ.updateBook(book);
	        return "redirect:/books";
	    }

	    @GetMapping("/newBook")
	    public String addBook(@ModelAttribute("book") Book book, HttpSession session) {
	        if(session.getAttribute("userId") == null) {
	            return "redirect:/logout";
	        }
	        return "new_book.jsp";
	    }

	    @PostMapping("/newBook")
	    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
	        Long userId = (Long) session.getAttribute("userId");
	        if(userId == null) {
	            return "redirect:/logout";
	        }
	        if(result.hasErrors()) {
	            return "new_book.jsp";
	        }
	        bookServ.addBook(new Book());
	        return "redirect:/books";
	    }

	    @RequestMapping("/books/delete/{id}")
	    public String deleteBook(@PathVariable("id") Long id, HttpSession session) {
	        if(session.getAttribute("userId") == null) {
	            return "redirect:/logout";
	        }
	        bookServ.deleteBook(bookServ.findBook(id));
	        return "redirect:/books";
	    }

	    @GetMapping("/bookmarket")
	    public String bookmarket(HttpSession session, Model model) {
	        Long userId = (Long) session.getAttribute("userId");
	        if(userId == null) {
	            return "redirect:/logout";
	        }
	        model.addAttribute("user", userServ.findById(userId));
	        List<Book> books = bookServ.unborrowedBooks(userServ.findById(userId));
	        model.addAttribute("books", books);
	        List<Book> myBooks = bookServ.borrowedBooks(userServ.findById(userId));
	        model.addAttribute("myBooks", myBooks);
	        return "bookmarket.jsp";
	    }

	    @RequestMapping("/bookmarket/{bookID}")
	    public String borrowBook(@PathVariable("bookID") Long bookID, HttpSession session) {
	        Long userId = (Long) session.getAttribute("userId");
	        if(userId == null) {
	            return "redirect:/logout";
	        }
	        bookServ.addBorrower(bookServ.findBook(bookID), userServ.findById(userId));
	        return "redirect:/bookmarket";
	    }

	    @RequestMapping("/bookmarket/return/{bookID}")
	    public String returnBook(@PathVariable("bookID") Long bookID, HttpSession session) {
	        if(session.getAttribute("userId") == null) {
	            return "redirect:/logout";
	        }
	        bookServ.removeBorrower(bookServ.findBook(bookID));
	        return "redirect:/bookmarket";
	    }
	}