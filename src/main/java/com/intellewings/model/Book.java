package com.intellewings.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	
	private String title;
	private String author;
	private String category;
	private String description;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonBackReference
	private User user;
}
