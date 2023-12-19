package com.example.project3client.model;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * This class is nearly identical to the server class, with the following exception:
 * - We don't use any of the Spring persistence that the server class has.
 */
public class User {
	private String userName;
	private String password;
	private LocalDateTime userExpiry;
	private String token;

	public User() {
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getUserExpiry() {
		return userExpiry;
	}

	public void setUserExpiry(LocalDateTime userExpiry) {
		this.userExpiry = userExpiry;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof User)) return false;
		User u = (User) o;
		return (this.userName.equals(u.userName));
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.userName);
	}

	@Override
	public String toString() {
		return "User{" + "userName=" + this.userName + '}';
	}

}