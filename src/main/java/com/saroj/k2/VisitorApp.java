package com.saroj.k2;

import java.sql.Date;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.repository.VisitorDAO;
import com.saroj.k2.repository.VisitorDAOImp;

public class VisitorApp {
	public static void main(String[] args) {
		Visitor visitor = new Visitor();
		visitor.setId(3);
		visitor.setName("rinku");
		visitor.setEmail("rinku@gmail.com");
		visitor.setPhone("9556410194");
		visitor.setGender("male");
		visitor.setDob(Date.valueOf("2003-02-26"));
		visitor.setAddress("bhadrak");
		visitor.setPassword("rinku@123");
		VisitorDAO dao = new VisitorDAOImp();
		System.out.println(dao.saveVisitor(visitor));

	}
}
