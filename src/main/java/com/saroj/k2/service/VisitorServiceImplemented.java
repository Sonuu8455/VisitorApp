package com.saroj.k2.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.repository.VisitorDAO;
import com.saroj.k2.repository.VisitorDAOImp;
import com.saroj.k2.util.AES;
import com.saroj.k2.util.AppConstants;

public class VisitorServiceImplemented implements VisitorService {
	private VisitorDAO dao;
	{
		dao = new VisitorDAOImp();
	}

	@Override
	public String saveVisitor(Visitor visitor) {
		visitor = encryptVisitor(visitor);
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
		visitor = encryptVisitor(visitor);
		if (visitor.getEmail() == null) {
			return AppConstants.noEmailPresent;
		}
		if (dao.getVisitorByEmail(visitor.getEmail()) == null) {
			return AppConstants.diffEmailEntered;
		}
		String msg = dao.updateRegisteredVisitor(visitor);
		if ((dao.getVisitorByEmail(visitor.getEmail()).getAge() >= 21)) {
			dao.updateValidVisitor(visitor);
		}
		return msg;
	}

	@Override
	public Visitor getVisitorById(int id) {
		Visitor visitor = dao.getVisitorById(id);
		visitor = decryptVisitor(visitor);
		return visitor;
	}

	@Override
	public Visitor getVisitorByEmail(String email) {
		Visitor visitor = dao.getVisitorByEmail(AES.encrypt(email, AppConstants.SECRET_KEY));
		visitor = decryptVisitor(visitor);
		return visitor;
	}

	@Override
	public Visitor deleteVisitorById(int id) {
		Visitor temp = dao.getVisitorById(id);
		Visitor visitor = dao.deleteRegisteredVisitorById(id);
		if (visitor != null) {
			if (temp.getAge() >= 21) {
				dao.deleteValidVisitorById(id);
			}
		}
		visitor = decryptVisitor(visitor);
		return visitor;
	}

	@Override
	public Visitor deleteVisitorByEmail(String email) {
		String enEmail = AES.encrypt(email, AppConstants.SECRET_KEY);
		Visitor temp = dao.getVisitorByEmail(enEmail);
		Visitor visitor = dao.deleteRegisteredVisitorByEmail(enEmail);
		if (visitor != null) {
			if (temp.getAge() >= 21) {
				dao.deleteValidVisitorByEmail(enEmail);
			}
		}
		visitor = decryptVisitor(visitor);
		return visitor;
	}

	@Override
	public List<Visitor> getAllRegisteredVisitor() {
		List<Visitor> registeredVisitor = dao.getAllRegisteredVisitor();
		List<Visitor> listVisitor = registeredVisitor.stream().map(e -> decryptVisitor(e)).collect(Collectors.toList());
		return listVisitor;
	}

	@Override
	public List<Visitor> getAllValidVisitor() {
		List<Visitor> validVisitor = dao.getAllValidVisitor();
		List<Visitor> listVisitor = validVisitor.stream().map(e -> decryptVisitor(e)).collect(Collectors.toList());
		return listVisitor;
	}

	@Override
	public Visitor visitorLogin(String email, String password) {
		Visitor visitor = dao.visitorLogin(AES.encrypt(email, AppConstants.SECRET_KEY),
				AES.encrypt(password, AppConstants.SECRET_KEY));
		visitor = decryptVisitor(visitor);
		return visitor;
	}

	@Override
	public List<Visitor> validVisitorSortedByName() {
		List<Visitor> allValidVisitor = dao.getAllValidVisitor();
		List<Visitor> list2 = allValidVisitor.stream().map(e -> decryptVisitor(e)).collect(Collectors.toList());
		List<Visitor> list = list2.stream().sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<Visitor> validVisitorSortedByEmail() {
		List<Visitor> allValidVisitor = dao.getAllValidVisitor();
		List<Visitor> list2 = allValidVisitor.stream().map(e -> decryptVisitor(e)).collect(Collectors.toList());
		List<Visitor> list = list2.stream().sorted((e1, e2) -> e1.getEmail().compareToIgnoreCase(e2.getEmail()))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<Visitor> registeredVisitorSortedByName() {
		List<Visitor> registeredVisitor = dao.getAllRegisteredVisitor();
		List<Visitor> list2 = registeredVisitor.stream().map(e -> decryptVisitor(e)).collect(Collectors.toList());
		List<Visitor> list = list2.stream().sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<Visitor> registeredVisitorSortedByEmail() {
		List<Visitor> registeredVisitor = dao.getAllRegisteredVisitor();
		List<Visitor> list2 = registeredVisitor.stream().map(e -> decryptVisitor(e)).collect(Collectors.toList());
		List<Visitor> list = list2.stream().sorted((e1, e2) -> e1.getEmail().compareToIgnoreCase(e2.getEmail()))
				.collect(Collectors.toList());
		return list;
	}

	private Visitor decryptVisitor(Visitor visitor) {
		visitor.setEmail(AES.decrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setPhone(AES.decrypt(visitor.getPhone(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.decrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPassword(AES.decrypt(visitor.getPassword(), AppConstants.SECRET_KEY));

		return visitor;
	}

	private Visitor encryptVisitor(Visitor visitor) {
		visitor.setEmail(AES.encrypt(visitor.getEmail(), AppConstants.SECRET_KEY));
		visitor.setPhone(AES.encrypt(visitor.getPhone(), AppConstants.SECRET_KEY));
		visitor.setAddress(AES.encrypt(visitor.getAddress(), AppConstants.SECRET_KEY));
		visitor.setPassword(AES.encrypt(visitor.getPassword(), AppConstants.SECRET_KEY));

		return visitor;
	}

}
