package com.bookbroker.bookbroker.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min=3, max=30)
    private String username;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Size(min=8, max=128)
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Book> ownedBooks;
    
    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private List<Book> borrowedBooks;
    
    public User() {
		// TODO Auto-generated constructor stub
	}

	public User(@NotBlank @Size(min = 3, max = 30) String username, @NotBlank @Email String email,
			@NotBlank @Size(min = 8, max = 128) String password, String passwordConfirmation, List<Book> ownedBooks,
			List<Book> borrowedBooks) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.ownedBooks = ownedBooks;
		this.borrowedBooks = borrowedBooks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public List<Book> getOwnedBooks() {
		return ownedBooks;
	}

	public void setOwnedBooks(List<Book> ownedBooks) {
		this.ownedBooks = ownedBooks;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", passwordConfirmation=" + passwordConfirmation + ", ownedBooks=" + ownedBooks + ", borrowedBooks="
				+ borrowedBooks + "]";
	}
    
    
}

