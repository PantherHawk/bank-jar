package com.pennypincherbank.Bank;

public class User {
	
	private int user_id;
	private String name;
	private String passhash;
	
	public User(String name, String passhash) {
		super();
		this.name		= name;
		this.passhash	= passhash;
	}

	public User() {
		super();
		this.name = "";
		this.passhash = "";
	}

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasshash() {
		return passhash;
	}

	public void setPasshash(String passhash) {
		this.passhash = passhash;
	}

	@Override
	public String toString() {
		return ("User with id " + this.user_id + " named " + this.name + " and password " + this.passhash);
	}
	
	

}
