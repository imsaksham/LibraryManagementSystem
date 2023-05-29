package com.intellewings.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Active_Session")
public class CurrentUserSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
	
	@Column(unique = true)
	private String username;

	private String uuid;
	private LocalDateTime localDateTime;
	
	public CurrentUserSession() {
		
	}
	
	public CurrentUserSession(Integer userId, String username, String uuid, LocalDateTime localDateTime) {
		this.sessionId = userId;
		this.username = username;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}
	
	public CurrentUserSession(String username, String uuid, LocalDateTime localDateTime) {
		this.username = username;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}
}
