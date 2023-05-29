package com.intellewings.service;

import java.util.List;

import com.intellewings.model.Book;
import com.intellewings.model.User;

public interface BookService {

	public Book addBookDetails(Book book, String username, String key);
	public List<Book> getAllAvailableBooks();
	public Integer totalNumberOfBooks();
	public Integer totalNumberOfBooksByTitle(String title);
	public Integer totalNumberOfBooksByAuthor(String author);
	public List<Book> searchBooksByTitle(String title);
	public List<Book> searchBooksByAuthor(String author);
	public List<Book> updateBookDetails(Book book, String username, String key);
	public String deleteBook(Book book, User user, Integer bookId, String username, String key);
}
