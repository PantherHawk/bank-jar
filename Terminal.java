package com.pennypincherbank.Bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

//import org.apache.log4j.Logger;

public class Terminal {
	private static Logger logger = Logger.getLogger("Terminal");

	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
//		welcome user to the bank
		System.out.println("Welcome to the penny pincher's bank!");
		System.out.println("Who are you?");
		String name = sc.nextLine();	
//		look up in db to find user 
//		if user ask for password
		System.out.println("What is your password?");
		String passhash = sc.nextLine();
//		otherwise handle no user found in db.
		System.exit(0);
		  
	}
}

//while ((line = scanner.nextLine()) != null) {
//	store = store + line;
//	if (line.trim().equalsIgnoreCase("exit")) {
//		scanner.close();
//		System.out.println("you wrote: " + store);
//		out.println(store);
//		System.exit(0);
//	}