package com.intellewings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellewings.model.CurrentUserSession;

@Repository
public interface UserSessionDao extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findByUuid(String uuid);
	public CurrentUserSession findByUsername(String username);
}
