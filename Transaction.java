package com.pennypincherbank.Bank;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {

	private int id;
	private int user_id;
	private int account_id;
	private Timestamp time;
	private String type;
	private int amount;
	
	public Transaction(int user_id, Timestamp time) {
		super();
		this.user_id	= user_id;
		this.time		= time;
	}
	public Transaction(int user_id, String type) {
		super();
		this.user_id	= user_id;
		this.type		= type;
	}
	public Transaction(int account_id, int user_id) {
		super();
		this.account_id = account_id;
	}
	public Transaction(int user_id) {
		super();
		this.user_id = user_id;
	}
	public Transaction(int id, int user_id, int account_id, Timestamp time, String type, int amount) {
		super();
		this.id			= id;
		this.user_id	= user_id;
		this.account_id	= account_id;
		this.time		= time;
		this.type		= type;
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
