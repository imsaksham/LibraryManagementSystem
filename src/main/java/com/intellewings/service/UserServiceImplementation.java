package com.intellewings.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellewings.exception.LoginException;
import com.intellewings.exception.UserAlreadyExistException;
import com.intellewings.exception.UserNotFoundException;
import com.intellewings.model.Book;
import com.intellewings.model.CurrentUserSession;
import com.intellewings.model.User;
import com.intellewings.repository.UserDao;
import com.intellewings.repository.UserSessionDao;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public User registerUser(User user) {
		
		User userEmail = userDao.findByEmail(user.getEmail());
		User username = userDao.findByUsername(user.getUsername());
		
		if (username != null) {
			throw new UserAlreadyExistException("Username is not available");
		}
		
		if (userEmail != null) {
			throw new UserAlreadyExistException("Email is already registered");
		}
		
		List<Book> books = new ArrayList<>();
		
		for (Book book: books) {
			book.setUser(user);
			user.getBooks().add(book);
		}
		return userDao.save(user);
	}

	@Override
	public String updateUser(User user, String username, String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null || !user.getUsername().equals(activeSession.getUsername())) {
			throw new LoginException("Please Log In first");
		}
		
		User existingUser = userDao.findByUsername(username);
		
		if (existingUser == null) {
			throw new UserNotFoundException("User Id is not valid");
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setUsername(user.getUsername());
		existingUser.setMobileNumber(user.getMobileNumber());
		existingUser.setPassword(user.getPassword());
		
		userSessionDao.delete(activeSession);
		
		userDao.save(existingUser);
		
		return "Data has been updated successfully. Please Log In again";
	}

	@Override
	public String deleteUser(User user, String username, String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null || !username.equals(activeSession.getUsername())) {
			throw new LoginException("Please Log In first");
		}
		
		User result = userDao.findByUsername(username);
		
		if (result == null) {
			throw new UserNotFoundException("User Id is not valid");
		}
		
		userDao.delete(result);
		userSessionDao.delete(activeSession);
		
		return "Your account has been deleted successfully";
	}
	
}
