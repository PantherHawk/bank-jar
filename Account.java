package com.pennypincherbank.Bank;

import java.util.Currency;
import java.time.LocalDateTime;;

public class Account {
	private int id;
	private int user_id;
	private LocalDateTime date_opened;
	private LocalDateTime date_close;
	private int curr_balance;
	private String name;

//	constructor can take as input the user_id, to connect it to the user, an initial balance, and a name.
	public Account(int user_id, int curr_balance, String name) {
		super();
		this.user_id		= user_id;
		this.curr_balance	= curr_balance;
		this.name			= name;
	}
	public Account(int id, int balance, int user_id, String name) {
		super();
		this.id = id;
		this.curr_balance = balance;
		this.user_id = user_id;
		this.name = name;
	}
	
//	an overridden constructor to generate a DAO with all the fields from the DB.
	public Account(int id, int user_id, LocalDateTime date_opened, LocalDateTime date_close, int curr_balance,
		String name) {
	super();
	this.id				= id;
	this.user_id		= user_id;
	this.date_opened	= date_opened;
	this.date_close		= date_close;
	this.curr_balance	= curr_balance;
	this.name			= name;
}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public LocalDateTime getDate_opened() {
		return date_opened;
	}
	public void setDate_opened(LocalDateTime date_opened) {
		this.date_opened = date_opened;
	}
	public LocalDateTime getDate_close() {
		return date_close;
	}
	public void setDate_close(LocalDateTime date_close) {
		this.date_close = date_close;
	}
	public int getCurr_balance() {
		return curr_balance;
	}
	public void setCurr_balance(int curr_balance) {
		this.curr_balance = curr_balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
