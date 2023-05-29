package com.intellewings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intellewings.model.Book;
import com.intellewings.model.User;
import com.intellewings.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	// {
	//  "bookId": 83,
	//  "title": "Sample Book 2",
	//  "author": "Author 2",
	//  "category": "Non-Fiction",
	//  "description": "Description 2"
	//}

	@Autowired
	private BookService bookService;
	
	// localhost:8088/book/add?username=johndoe&key=086c
	@PostMapping("/add")
	public ResponseEntity<Book> addBookDetails(@RequestBody Book book, @RequestParam String username, @RequestParam String key) {
		Book addeddBook = bookService.addBookDetails(book, username, key);
		
		return new ResponseEntity<>(addeddBook, HttpStatus.CREATED);
	}
	
	// localhost:8088/book/available
	@GetMapping("/available")
	public ResponseEntity<List<Book>> getAllAvailableBooks() {
		List<Book> availableBooks = bookService.getAllAvailableBooks();
		
		return new ResponseEntity<>(availableBooks, HttpStatus.OK);
	}
	
	// localhost:8088/book/total-number-books
	@GetMapping("/total-number-books")
	public ResponseEntity<Integer> totalNumberOfBooks() {
		Integer total = bookService.totalNumberOfBooks();
		
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
	
	// localhost:8088/book/total-number-books-by-title/sample1
	@GetMapping("/total-number-books-by-title/{title}")
	public ResponseEntity<Integer> totalNumberOfBooksByTitle(@PathVariable("title") String title) {
		Integer total = bookService.totalNumberOfBooksByTitle(title);
		
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
	
	// localhost:8088/book/total-number-books-by-author/Author
	@GetMapping("/total-number-books-by-author/{author}")
	public ResponseEntity<Integer> totalNumberOfBooksByAuthor(@PathVariable("author") String author) {
		Integer total = bookService.totalNumberOfBooksByAuthor(author);
		
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
	
	// localhost:8088/book/search-books-by-title/sample1
	@GetMapping("/search-books-by-title/{title}")
	public ResponseEntity<List<Book>> searchBooksByTitle(@PathVariable("title") String title) {
		List<Book> result = bookService.searchBooksByTitle(title);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// localhost:8088/book/search-books-by-author/Author
	@GetMapping("/search-books-by-author/{author}")
	public ResponseEntity<List<Book>> searchBooksByAuthor(@PathVariable("author") String author) {
		List<Book> result = bookService.searchBooksByAuthor(author);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// localhost:8088/book/update?key=24e7
	@PutMapping("/update")
	public ResponseEntity<List<Book>> updateBookDetails(@RequestBody Book book, String username, @RequestParam String key) {
		List<Book> updatedBooks = bookService.updateBookDetails(book, username, key);
		
		return new ResponseEntity<>(updatedBooks, HttpStatus.OK);
	}
	
	// localhost:8088/book/delete?bookId=81&username=rahul&key=24e7
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteBook(Book book, User user, @RequestParam Integer bookId, @RequestParam String username, @RequestParam String key) {
		String deletedBook = bookService.deleteBook(book, user, bookId, username, key);
		
		return new ResponseEntity<>(deletedBook, HttpStatus.MOVED_PERMANENTLY);
	}
}
