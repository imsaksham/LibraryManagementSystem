package com.intellewings.service;

import com.intellewings.model.User;

public interface UserService {

	public User registerUser(User user);
	public String updateUser(User user, String username, String key);
	public String deleteUser(User user, String username, String Key);
}
