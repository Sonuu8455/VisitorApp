package com.saroj.k2.controller;

import java.sql.Date;

public class AppController {
	//Communicate with user
	public static void main(String[] args) {
		Date dob=null;
//		String d="2000-02-26";
//		dob=new Date(LocalDate.of(2000, 02, 26));
//		dob=Date.valueOf(LocalDate.of(2000, 02, 26));
		dob=Date.valueOf("2000-02-26");
		System.out.println(dob);
		
		
	}
}
/*
 * admin login				provide user,pass -> enter or back or exit -> if valid -> admin menu 
 * visitor login			provide user,pass -> enter or back or exit -> if valid -> visitor menu
 * create new registration
 * exit
 * 
 */