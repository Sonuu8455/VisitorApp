package com.saroj.k2.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.repository.VisitorDAO;
import com.saroj.k2.repository.VisitorDAOImp;

public class VisitorServiceImplemented implements VisitorService {

	@Override
	public String saveVisitor(Visitor visitor) {
		VisitorDAO visitorDAO = new VisitorDAOImp();
		int age = calculateAge(visitor.getDob());
		visitor.setAge(age);
		String msg = visitorDAO.saveRegisteredVisitor(visitor);
		if (age >= 21) {
			visitorDAO.saveValidVisitor(visitor);
		}
		return msg;
	}

	private int calculateAge(Date dob) {
		return Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
	}

	@Override
	public String updateVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visitor getVisitorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visitor deleteVisitorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> getAllRegisteredVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visitor> getAllValidVisitor() {
		// TODO Auto-generated method stub
		return null;
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
