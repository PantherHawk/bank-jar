package com.pennypincherbank.Bank;

public class User {
	
	private int id;
	private String name;
	private String passhash;
	
	public User(int id, String name, String passhash) {
		super();
		this.id			= id;
		this.name		= name;
		this.passhash	= passhash;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User() {
		super();
		this.id = id;
		this.name = "";
		this.passhash = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return ("User with id " + this.id + " named " + this.name + " and password " + this.passhash);
	}
	
	

}
