package com.saroj.k2.controller;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.service.VisitorService;
import com.saroj.k2.service.VisitorServiceImplemented;

public class AppController {
	//Communicate with user
	public static void main(String[] args) {
		VisitorService service = new VisitorServiceImplemented();
		
//		Date dob=Date.valueOf(LocalDate.of(2007, 02, 26));
//		Visitor visitor = new Visitor(7, "sss", "sss@gmail.com", "987654321", "male", dob, null, "jajpur", "sss@8455");
		Visitor visitor2=new Visitor();
		visitor2.setName("ttt");
		visitor2.setPhone("54984894956");
		visitor2.setAddress("sdfgdfgdfg");
		visitor2.setPassword("ttt@5444");
		visitor2.setEmail("sdfsdf@gmail.com");
		
		System.out.println(service.updateVisitor(visitor2));
//		System.out.println(service.saveVisitor(visitor));
	}
}





























/*
 * admin login				provide user,pass -> enter or back or exit -> if valid -> admin menu 
 * visitor login			provide user,pass -> enter or back or exit -> if valid -> visitor menu
 * create new registration
 * exit
 * 		Date dob=null;
		String d="2000-02-26";
		dob=new Date(LocalDate.of(2000, 02, 26));
		dob=Date.valueOf(LocalDate.of(2000, 02, 26));
		dob=Date.valueOf("2000-02-26");
		System.out.println(dob);
 */