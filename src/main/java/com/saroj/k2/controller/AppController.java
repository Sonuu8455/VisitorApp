package com.saroj.k2.controller;

import com.saroj.k2.service.VisitorService;
import com.saroj.k2.service.VisitorServiceImplemented;

public class AppController {
	//Communicate with user
	public static void main(String[] args) {
		VisitorService service = new VisitorServiceImplemented();
		
//		Date dob=Date.valueOf(LocalDate.of(1995, 02, 26));
//		Visitor visitor = new Visitor(8, "chinu", "chinu@gmail.com", "9853269909", "female", dob, null, " bhadrak", "chinu@8455");
//		Visitor visitor2=new Visitor();
//		visitor2.setName("hhhhhuhsdufhh");
//		visitor2.setPhone("00000000");
//		visitor2.setAddress("j2ee");
//		visitor2.setPassword("jhand@8455");
//		visitor2.setEmail("sss@gmail.com");
		
		System.out.println(service.getVisitorById(8));
//		System.out.println(service.getVisitorByEmail("saroj@gmail.com"));
//		System.out.println(service.deleteVisitorById(8));
//		System.out.println(service.saveVisitor(visitor));
//		service.getAllRegisteredVisitor().forEach(System.out::println);
	}
}





























/*
 * admin login				provide user,pass -> enter or back or exit -> if valid -> admin menu 
 * visitor login			provide user,pass -> enter or back or exit -> if valid -> visitor menu
 * create new registration
 * exit
 * 		Date dob=null;
 *		String d="2000-02-26";
 *		dob=new Date(LocalDate.of(2000, 02, 26));
 *		dob=Date.valueOf(LocalDate.of(2000, 02, 26));
 *		dob=Date.valueOf("2000-02-26");
 *		System.out.println(dob);
 *
 *
 *  Download tomcat version 9
 *  
 *  create maven web app version 1.4 --> org.apache.maven --> maven-archetype-webapp 1.4 --> 
 *  group id-com.saroj, Artifact id-ServletIntro
 *  
 *  there will bw two errors in project
 *  
 *  deployment descripter : archetype created web application
 *  java resorces
 *  deploy resoeces
 *  src
 *  target
 *  pom.xml
 *  
 *  
 *  add servlet API dependency version 4.0.1 to  the project's pom.xml file
 *  
 */