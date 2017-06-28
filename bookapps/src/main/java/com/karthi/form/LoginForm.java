package com.karthi.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {
	@NotNull
	@Size(max = 50, message = "Email cannot exceed 50 characters")
	private String email;

	@NotNull
	
	@NotBlank(message = "Password cannot be empty")
	@Size.List({ @Size(min = 8, message = "Password  must be minimum 8 characters"),
			@Size(max = 30, message = "Password cannot exceed 30 characters") })
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
