package com.saroj.k2;

import com.saroj.k2.repository.AdminDAO;
import com.saroj.k2.repository.AdminDAOImp;

public class VisitorApp {
	public static void main(String[] args) {
//		VisitorDAO dao = new VisitorDAOImp();
		AdminDAO ad = new AdminDAOImp();

//		Visitor visitor = new Visitor();
//		visitor.setId(3);
//		visitor.setName("rinku");
//		visitor.setEmail("rinku@gmail.com");
//		visitor.setPhone("9556410194");
//		visitor.setGender("male");
//		visitor.setDob(Date.valueOf("2003-02-26"));
//		visitor.setAddress("bhadrak");
//		visitor.setPassword("rinku@123");
//		System.out.println(dao.saveVisitor(visitor));

//		System.out.println(dao.getVisitorById(2));

//		dao.getAllRegisteredVisitor().forEach(System.out::println);

//		dao.getAllValidVisitor().forEach(System.out::println);

//		System.out.println(dao.deleteVisitorById(3));

//		System.out.println(dao.visitorLogin("saroj@gmail.com", "saroj@123"));

//		Admin admin = new Admin();
//		admin.setId(3);
//		admin.setUserName("rinku");
//		admin.setPassword("rinku@8455");
//		System.out.println(ad.saveAdmin(admin));
		System.out.println(ad.adminLogin("raja", "raja@8455"));
	}
}
