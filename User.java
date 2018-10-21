package com.pennypincherbank.Bank;

public class User {
	
	private int user_id;
	private int user_role;
	private int isRegistered;
	private String username;
	private String firstName;
	private String lastName;
	private String passhash;
	
	public User(int user_id,int user_role, int isRegistered, String username, String passhash, String firstName, String lastName) {
		super();
		this.user_id	= user_id;
		this.username	= username;
		this.firstName	= firstName;
		this.lastName	= lastName;
		this.passhash	= passhash;
		this.isRegistered = isRegistered;
		this.user_role = user_role;
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

	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	public int getIsRegistered() {
		return isRegistered;
	}
	public void setIsRegistered(int isRegistered) {
		this.isRegistered = isRegistered;
	}
	@Override
	public String toString() {
		return ("id =  " + this.user_id + 
				"\n username = " + this.username +
				"\n password = " + this.passhash +
				"\n first name = " + this.firstName + 
				"\n last name = " + this.lastName + 
				"\n role level = " + this.user_role + 
				"\n registered = " + this.isRegistered
				);
	}
	
	

}
