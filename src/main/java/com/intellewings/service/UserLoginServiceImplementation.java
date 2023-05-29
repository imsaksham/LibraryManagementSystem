package com.intellewings.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellewings.exception.LoginException;
import com.intellewings.model.CurrentUserSession;
import com.intellewings.model.User;
import com.intellewings.model.UserLoginDTO;
import com.intellewings.repository.UserDao;
import com.intellewings.repository.UserSessionDao;

@Service
public class UserLoginServiceImplementation implements UserLoginService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public String userLogIn(UserLoginDTO userLoginDTO) throws LoginException {
		User existingUser = userDao.findByUsername(userLoginDTO.getUsername());
		
		if (existingUser == null) {
			throw new LoginException("Please enter a valid username");
		}
		
		
		CurrentUserSession opt = userSessionDao.findByUsername(existingUser.getUsername());
		
		if (opt != null) {
			throw new LoginException("User already logged in");
		}
		
		if (existingUser.getPassword().equals(userLoginDTO.getPassword())) {
			
			String key = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
			
			CurrentUserSession activeSession = new CurrentUserSession(existingUser.getUsername(), key, LocalDateTime.now());
			
			userSessionDao.save(activeSession);
			
			return activeSession.toString();
		}
		else {
			throw new LoginException("Please enter a valid password");
		}
	}

	@Override
	public String userLogOut(String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null) {
			throw new LoginException("User not logged in");
		}
		
		userSessionDao.delete(activeSession);
		
		return "Successfully Logged out!";
	}
}
