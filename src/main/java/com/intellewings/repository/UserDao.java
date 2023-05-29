package com.intellewings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intellewings.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
	public User findByEmail(String email);
}
