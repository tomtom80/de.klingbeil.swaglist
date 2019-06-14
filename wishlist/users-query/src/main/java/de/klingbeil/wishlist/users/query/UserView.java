package de.klingbeil.wishlist.users.query;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import de.klingbeil.wishlist.core.api.users.UserAccount;

@Entity
public class UserView implements UserAccount, Serializable {

	private static final long serialVersionUID = 1312512676934300069L;

	@Id
	@javax.persistence.Id
	private String identifier;
	private String name;
	private String username;
	@SuppressWarnings("unused")
	private String password;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUserId() {
		return this.identifier;
	}

	@Override
	public String getUserName() {
		return this.username;
	}

	@Override
	public String getFullName() {
		return this.name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
