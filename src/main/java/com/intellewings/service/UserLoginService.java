package com.intellewings.service;

import com.intellewings.exception.LoginException;
import com.intellewings.model.UserLoginDTO;

public interface UserLoginService {

	public String userLogIn(UserLoginDTO userLoginDTO) throws LoginException;
	public String userLogOut(String key) throws LoginException;
}
