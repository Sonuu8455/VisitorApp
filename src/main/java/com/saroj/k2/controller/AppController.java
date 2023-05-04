package com.saroj.k2.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.service.VisitorService;
import com.saroj.k2.service.VisitorServiceImplemented;

public class AppController {
	public static void main(String[] args) {
		VisitorService service = new VisitorServiceImplemented();

		Scanner sc = new Scanner(System.in);

		boolean outerFlag = true;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("0. Visitor Log In\n1. Admin Loh In\n2. New Visitor Registration\n3. Exit");
			int key = sc.nextInt();
			switch (key) {
			case 0:
				System.out.println("Enter email");
				String email = sc.next();
				System.out.println("Enter Password");
				String pass = sc.next();
				if (service.getVisitorByEmail(email) != null) {
					if (service.getVisitorByEmail(email).getPassword().equals(pass)) {
						System.out.println("---------------------------------------------");
						System.out.println("Log In Successfull");
						System.out.println("---------------------------------------------");
						boolean innerFlag = true;
						do {
							System.out.println("1. Get your details\n" + "2. get all registered visitor details\n"
									+ "3. Get all valid visitor details.\n"
									+ "4. Get all registered visitor sorted by name\n"
									+ "5. Get all valid visitor sorted by name\n" + "6. Exit");
							int keyInner = sc.nextInt();
							switch (keyInner) {
							case 1:
								System.out.println("---------------------------------------------");
								System.out.println(service.getVisitorByEmail(email));
								System.out.println("---------------------------------------------");
								break;
							case 2:
								System.out.println("---------------------------------------------");
								service.getAllRegisteredVisitor().forEach(System.out::println);
								System.out.println("---------------------------------------------");
								break;
							case 3:
								System.out.println("---------------------------------------------");
								service.getAllValidVisitor().forEach(System.out::println);
								System.out.println("---------------------------------------------");
								break;
							case 4:
								System.out.println("---------------------------------------------");
								service.registeredVisitorSortedByName().forEach(System.out::println);
								System.out.println("---------------------------------------------");
								break;
							case 5:
								System.out.println("---------------------------------------------");
								service.validVisitorSortedByName().forEach(System.out::println);
								System.out.println("---------------------------------------------");
								break;
							case 6:
								innerFlag = false;
								break;
							default:
								System.out.println("Enter a valid option...");
								break;
							}
						} while (innerFlag);
					} else {
						System.out.println("Password is incorrect. Enter a valid password");
					}
				} else {
					System.out.println("---------------------------------------------");
					System.out.println("Plsease register first.");
				}

				break;
			case 1:
				System.out.println("tthis function is under development");
				break;

			case 2:
				Visitor v = new Visitor();
				v.setId(9);
				System.out.println("Enter name");
				v.setName(sc.nextLine());
				System.out.println("Enter email");
				v.setEmail(sc.nextLine());
				System.out.println("Enter Phone Number");
				v.setPhone(sc.nextLine());
				System.out.println("Enter genger");
				v.setGender(sc.nextLine());
				System.out.println("Enter birth year");
				int year = sc.nextInt();
				System.out.println("Enter birth month");
				int month = sc.nextInt();
				System.out.println("Enter birth day");
				int day = sc.nextInt();
				v.setDob(Date.valueOf(LocalDate.of(year, month, day)));
				System.out.println("Enter your address");
				v.setAddress(sc.nextLine());
				System.out.println("Enter password");
				v.setPassword(sc.nextLine());
				System.out.println();
				System.out.println(service.saveVisitor(v));
				break;

			case 3:
				outerFlag = false;
				break;

			default:
				break;
			}
		} while (outerFlag);

//		Date dob=Date.valueOf(LocalDate.of(2000, 04, 03));
//		Visitor visitor = new Visitor(1, "saroj", "saroj@gmail.com", "8455802077", "male", dob, null, "bengaluru", "saroj@8455");
//		Visitor visitor2=new Visitor();
//		visitor2.setName("hhhhhuhsdufhh");
//		visitor2.setPhone("00000000");
//		visitor2.setAddress("j2ee");
//		visitor2.setPassword("jhand@8455");
//		visitor2.setEmail("rrr1@gmail.com");
//		
//		System.out.println(service.saveVisitor(visitor));
//		
//		System.out.println(service.updateVisitor(visitor2));
//
//		System.out.println(service.getVisitorById(8));
//		
//		System.out.println(service.getVisitorByEmail("shuvam@gmail.com"));
//		
//		System.out.println(service.deleteVisitorByEmail("chinu@gmail.com"));
//		
//		System.out.println(service.deleteVisitorById(8));
//		
//		System.out.println(service.visitorLogin("chinu@gmail.com", "chinu@8455"));
//		
//		service.getAllRegisteredVisitor().forEach(System.out::println);
//		
//		service.getAllValidVisitor().forEach(System.out::println);
//		
//		service.validVisitorSortedByName().forEach(System.out::println);
//
//		service.registeredVisitorSortedByName().forEach(System.out::println);
//		
//		service.validVisitorSortedByEmail().forEach(System.out::println);
//		
//		service.registeredVisitorSortedByEmail().forEach(System.out::println);

		sc.close();
	}
}

/*
 * admin login provide user,pass -> enter or back or exit -> if valid -> admin
 * menu visitor login provide user,pass -> enter or back or exit -> if valid ->
 * visitor menu create new registration exit Date dob=null; String
 * d="2000-02-26"; dob=new Date(LocalDate.of(2000, 02, 26));
 * dob=Date.valueOf(LocalDate.of(2000, 02, 26)); dob=Date.valueOf("2000-02-26");
 * System.out.println(dob);
 *
 *
 * Download tomcat version 9
 * 
 * create maven web app version 1.4 --> org.apache.maven -->
 * maven-archetype-webapp 1.4 --> group id-com.saroj, Artifact id-ServletIntro
 * 
 * there will bw two errors in project
 * 
 * deployment descripter : archetype created web application java resorces
 * deploy resoeces src target pom.xml
 * 
 * 
 * add servlet API dependency version 4.0.1 to the project's pom.xml file
 * 
 */