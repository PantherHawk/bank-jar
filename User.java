package com.pennypincherbank.Bank;

public class User {
	
	private int user_id;
	private String username;
	private String firstName;
	private String lastName;
	private String passhash;
	
	public User(String firstName, String lastName, String passhash) {
		super();
		this.firstName		= firstName;
		this.lastName = lastName;
		this.passhash	= passhash;
	}
	public User(int id, String username, String password) {
		super();
		this.user_id = id;
		this.username = username;
		this.passhash = password;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.passhash = password;
	}
	public User() {
		super();
		this.username = "";
		this.passhash = "";
	}

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getPasshash() {
		return passhash;
	}

	public void setPasshash(String passhash) {
		this.passhash = passhash;
	}

	@Override
	public String toString() {
		return ("User with id " + this.user_id + " named " + this.firstName + " " + this.lastName + " and password " + this.passhash);
	}
	
	

}
