package com.saroj.k2.repository;

import java.util.List;

import com.saroj.k2.DTO.Visitor;

public interface VisitorDAO {

	public String saveRegisteredVisitor(Visitor visitor);

	public String saveValidVisitor(Visitor visitor);

	public String updateRegisteredVisitor(Visitor visitor);

	public String updateValidVisitor(Visitor visitor);

	public Visitor getVisitorById(int id);

	public Visitor deleteRegisteredVisitorById(int id);

	public Visitor deleteValidVisitorById(int id);

	public Visitor deleteRegisteredVisitorByEmail(String email);

	public Visitor deleteValidVisitorByEmail(String email);

	public List<Visitor> getAllRegisteredVisitor();

	public List<Visitor> getAllValidVisitor();

	public Visitor visitorLogin(String email, String password);

	public Visitor getVisitorByEmail(String email);
}
