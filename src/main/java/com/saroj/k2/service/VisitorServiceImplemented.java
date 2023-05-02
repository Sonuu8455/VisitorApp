package com.saroj.k2.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.repository.VisitorDAO;
import com.saroj.k2.repository.VisitorDAOImp;
import com.saroj.k2.util.AES;
import com.saroj.k2.util.AppConstants;

public class VisitorServiceImplemented implements VisitorService {
	private VisitorDAO dao;
	{
		dao=new VisitorDAOImp();
	}

	@Override
	public String saveVisitor(Visitor visitor) {
		String enEmail = AES.encrypt(visitor.getEmail(), AppConstants.SECRET_KEY);
		String enPhone =AES.encrypt(visitor.getPhone(), AppConstants.SECRET_KEY);
		String enAddress =AES.encrypt(visitor.getAddress(), AppConstants.SECRET_KEY);
		String enPassword =AES.encrypt(visitor.getPassword(), AppConstants.SECRET_KEY);
		
		visitor.setEmail(enEmail);
		visitor.setPhone(enPhone);
		visitor.setAddress(enAddress);
		visitor.setPassword(enPassword);
		
//		VisitorDAO dao = new VisitorDAOImp();
		int age = calculateAge(visitor.getDob());
		visitor.setAge(age);
		String msg = dao.saveRegisteredVisitor(visitor);
		if (age >= 21) {
			dao.saveValidVisitor(visitor);
		}
		return msg;
	}

	private int calculateAge(Date dob) {
		return Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
	}

	@Override
	public String updateVisitor(Visitor visitor) {
		VisitorDAO dao = new VisitorDAOImp();
		String msg = dao.updateRegisteredVisitor(visitor);
		if ((dao.getVisitorByEmail(visitor.getEmail()).getAge()>=21)) {
			dao.updateValidVisitor(visitor);
		}
		return msg;
	}

	@Override
	public Visitor getVisitorById(int id) {
		VisitorDAO dao = new VisitorDAOImp();
		Visitor visitor = dao.getVisitorById(id);
		visitor.setEmail(AES.decrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setPhone(AES.decrypt(visitor.getPhone(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.decrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPassword(AES.decrypt(visitor.getPassword(), AppConstants.SECRET_KEY));
		return visitor;
	}

	@Override
	public Visitor getVisitorByEmail(String email) {
		VisitorDAO dao=new VisitorDAOImp();
		return dao.getVisitorByEmail(email);
	}

	@Override
	public Visitor deleteVisitorById(int id) {
		VisitorDAO dao =new VisitorDAOImp();
		Visitor temp = dao.getVisitorById(id);
		Visitor visitor = dao.deleteRegisteredVisitorById(id);
		if (temp.getAge()>=21) {
			dao.deleteValidVisitorById(id);
		}
		return visitor;
	}

	@Override
	public Visitor deleteVisitorByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> getAllRegisteredVisitor() {
		VisitorDAO dao = new VisitorDAOImp();
		return dao.getAllRegisteredVisitor();
	}

	@Override
	public List<Visitor> getAllValidVisitor() {
		VisitorDAO dao = new VisitorDAOImp();
		return dao.getAllValidVisitor();
	}

	@Override
	public Visitor visitorLogin(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> validVisitorSortedByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> validVisitorSortedByEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> registeredVisitorSortedByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> registeredVisitorSortedByEmail() {
		// TODO Auto-generated method stub
		return null;
	}

}
