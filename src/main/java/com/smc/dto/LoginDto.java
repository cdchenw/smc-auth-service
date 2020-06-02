package com.smc.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for storing a user's credentials.
 */
public class LoginDto {

	@NotNull
	@Size(min = 1, max = 50)
	private String email;

	@NotNull
	@Size(min = 4, max = 100)
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

	@Override
	public String toString() {
		return "LoginVM{" + "email='" + email + '\'' + '}';
	}
}
