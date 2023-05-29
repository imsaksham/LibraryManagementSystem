 package com.intellewings.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name must not contain numbers or special characters")
	private String firstName;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must not contain numbers or special characters")
	private String lastName;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please input a valid username which contain lowercase character and length should be minimum 3 and maximum 20")
	private String username;
	
	@NotNull
    @NotBlank
    @Column(unique = true)
	@Email(message = "Please write the valid email")
	private String email;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must have 10 digits")
	private String mobileNumber;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$", message = "Password must be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Book> books = new ArrayList<>();
}
