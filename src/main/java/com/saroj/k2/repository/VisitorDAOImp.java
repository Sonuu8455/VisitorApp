package com.saroj.k2.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.saroj.k2.DTO.Visitor;

public class VisitorDAOImp implements VisitorDAO {

	public String saveVisitor(Visitor visitor) {
		Connection connection = ConnectionGiver.getCreatedConnection();
		String registeredVisitorQuery = "INSERT INTO registered_visitor VALUES (?,?,?,?,?,?,?,?,?)";
		String validVisitorQuery = "INSERT INTO valid_visitor VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			// for registered_visitor table
			PreparedStatement ps1 = connection.prepareStatement(registeredVisitorQuery);
			ps1.setInt(1, visitor.getId());
			ps1.setString(2, visitor.getName());
			ps1.setString(3, visitor.getEmail());
			ps1.setString(4, visitor.getPhone());
			ps1.setString(5, visitor.getGender());
			ps1.setDate(6, visitor.getDob());
			int age = getAge(visitor.getDob());
			ps1.setInt(7, age);
			ps1.setString(8, visitor.getAddress());
			ps1.setString(9, visitor.getPassword());

			int res1 = ps1.executeUpdate();

			// for valid_visitor table
			int res2=0;
			if (age >= 18) {
				PreparedStatement ps2 = connection.prepareStatement(validVisitorQuery);
				ps2.setInt(1, visitor.getId());
				ps2.setString(2, visitor.getName());
				ps2.setString(3, visitor.getEmail());
				ps2.setString(4, visitor.getPhone());
				ps2.setString(5, visitor.getGender());
				ps2.setDate(6, visitor.getDob());
				ps2.setInt(7, age);
				ps2.setString(8, visitor.getAddress());
				ps2.setString(9, visitor.getPassword());

				res2 = ps2.executeUpdate();
			}

			connection.close();

			return res1 + " rows inserted to registered_visitor table and "+res2+" rows inserted to valid_visitor table";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "no rows inserted";
	}

	private int getAge(Date dob) {
		return Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
	}

	public String updateVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Visitor getVisitorById(int id) {
		return null;
	}

	public Visitor deleteVisitorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Visitor> getAllRegisteredVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Visitor> getAllValidVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Visitor visitorLogin(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
