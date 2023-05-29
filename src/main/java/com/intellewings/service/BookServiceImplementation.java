package com.intellewings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellewings.exception.LoginException;
import com.intellewings.exception.UserNotFoundException;
import com.intellewings.model.Book;
import com.intellewings.model.CurrentUserSession;
import com.intellewings.model.User;
import com.intellewings.repository.BookDao;
import com.intellewings.repository.UserDao;
import com.intellewings.repository.UserSessionDao;

@Service
public class BookServiceImplementation implements BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Override
	public Book addBookDetails(Book book, String username, String key) {
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		System.out.println(activeSession.getUsername());

	    if (activeSession == null || !username.equals(activeSession.getUsername())) {
	        throw new LoginException("Please Log In first");
	    }

	    User existingUser = userDao.findByUsername(username);

	    if (existingUser == null) {
	        throw new UserNotFoundException("User Id is not valid");
	    }

	    book.setUser(existingUser);

	    return bookDao.save(book);
	}

	@Override
	public List<Book> getAllAvailableBooks() {
		return bookDao.findAll();
	}
	
	@Override
	public Integer totalNumberOfBooks() {
		return bookDao.findAll().size();
	}

	@Override
	public Integer totalNumberOfBooksByTitle(String title) {
		return bookDao.countByTitle(title);
	}

	@Override
	public Integer totalNumberOfBooksByAuthor(String author) {
		return bookDao.countByAuthor(author);
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		return bookDao.findByTitle(title);
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		return bookDao.findByAuthor(author);
	}

	@Override
	public List<Book> updateBookDetails(Book book, String username, String key) {
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null || !username.equals(activeSession.getUsername())) {
			throw new LoginException("Please Log In first");
		}
		
		User existingUser = userDao.findByUsername(username);
		
		if (existingUser == null) {
			throw new UserNotFoundException("You don't have any book with this username");
		}
		
		List<Book> existingBooks = existingUser.getBooks();
		
		for (Book existingBook: existingBooks) {
			if (existingBook.getBookId().equals(book.getBookId())) {
				existingBook.setAuthor(book.getAuthor());
				existingBook.setCategory(book.getCategory());
				existingBook.setTitle(book.getTitle());
				existingBook.setDescription(book.getDescription());
				existingBook.setUser(existingUser);
				break;
			}
		}
		
		return bookDao.saveAll(existingBooks);
	}

	@Override
	public String deleteBook(Book book, User user, Integer bookId, String username, String key) {
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null || !username.equals(activeSession.getUsername())) {
			throw new LoginException("Please Log In first");
		}
		
		User existingUser = userDao.findByUsername(username);
		
		if (existingUser == null) {
			throw new UserNotFoundException("You don't have any book with this username");
		}
		
		List<Book> existingBooks = existingUser.getBooks();
		
		Boolean flag = false;
		
		for (Book existingBook: existingBooks) {
			if (bookId.equals(existingBook.getBookId())) {
				existingBooks.remove(existingBook);
				bookDao.delete(existingBook);
				flag = true;
				break;
			}
		}
		
		if (flag == false) {
			throw new UserNotFoundException("You don't have any book with this username");
		}
		return "Book has been deleted successfully";
	}

}
