package com.saroj.k2.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.saroj.k2.DTO.Visitor;

public class VisitorDAOImp implements VisitorDAO {

	public String saveVisitor(Visitor visitor) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String registeredVisitorQuery = "INSERT INTO registered_visitor VALUES (?,?,?,?,?,?,?,?,?)";
		String validVisitorQuery = "INSERT INTO valid_visitor VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			// for registered_visitor table
			PreparedStatement ps1 = con.prepareStatement(registeredVisitorQuery);
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
			int res2 = 0;
			if (age >= 18) {
				PreparedStatement ps2 = con.prepareStatement(validVisitorQuery);
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

			con.close();

			return res1 + " rows inserted to registered_visitor table and " + res2
					+ " rows inserted to valid_visitor table";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "no rows inserted";
	}

	private int getAge(Date dob) {
		return Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
	}

	public String updateVisitor(Visitor visitor) {

		return null;
	}

	public Visitor getVisitorById(int id) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String query = "SELECT * FROM registered_visitor WHERE id=?";
		Visitor visitor = null;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				visitor = new Visitor();
				visitor.setId(set.getInt(1));
				visitor.setName(set.getString(2));
				visitor.setEmail(set.getString(3));
				visitor.setPhone(set.getString(4));
				visitor.setGender(set.getString(5));
				visitor.setDob(set.getDate(6));
				visitor.setAge(set.getInt(7));
				visitor.setAddress(set.getString(8));
				visitor.setPassword(set.getString(9));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visitor;
	}

	public Visitor deleteVisitorById(int id) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String queryForDelete = "DELETE FROM registered_visitor WHERE id=?";
		String queryForReturn = "SELECT * FROM registered_visitor WHERE id=?";
		String query2 = "DELETE FROM valid_visitor WHERE id=?";
		Visitor visitor = null;
		try {
			PreparedStatement ps = con.prepareStatement(queryForReturn);
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				visitor = new Visitor();
				visitor.setId(set.getInt(1));
				visitor.setName(set.getString(2));
				visitor.setEmail(set.getString(3));
				visitor.setPhone(set.getString(4));
				visitor.setGender(set.getString(5));
				visitor.setDob(set.getDate(6));
				visitor.setAge(set.getInt(7));
				visitor.setAddress(set.getString(8));
				visitor.setPassword(set.getString(9));
			}
			PreparedStatement ps1 = con.prepareStatement(queryForDelete);
			ps1.setInt(1, id);
			ps1.executeUpdate();
			if (set.getInt(7) >= 18) {
				PreparedStatement ps2 = con.prepareStatement(query2);
				ps2.setInt(1, id);
				ps2.executeUpdate();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visitor;
	}

	public List<Visitor> getAllRegisteredVisitor() {
		List<Visitor> list = null;
		Connection con = ConnectionGiver.getCreatedConnection();
		String query = "SELECT * FROM registered_visitor";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				Visitor visitor = new Visitor();
				visitor = new Visitor();
				visitor.setId(set.getInt(1));
				visitor.setName(set.getString(2));
				visitor.setEmail(set.getString(3));
				visitor.setPhone(set.getString(4));
				visitor.setGender(set.getString(5));
				visitor.setDob(set.getDate(6));
				visitor.setAge(set.getInt(7));
				visitor.setAddress(set.getString(8));
				visitor.setPassword(set.getString(9));

				list.add(visitor);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Visitor> getAllValidVisitor() {
		List<Visitor> list = null;
		Connection con = ConnectionGiver.getCreatedConnection();
		String query = "SELECT * FROM valid_visitor";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				Visitor visitor = new Visitor();
				visitor = new Visitor();
				visitor.setId(set.getInt(1));
				visitor.setName(set.getString(2));
				visitor.setEmail(set.getString(3));
				visitor.setPhone(set.getString(4));
				visitor.setGender(set.getString(5));
				visitor.setDob(set.getDate(6));
				visitor.setAge(set.getInt(7));
				visitor.setAddress(set.getString(8));
				visitor.setPassword(set.getString(9));

				list.add(visitor);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Visitor visitorLogin(String email, String password) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String query = "SELECT * FROM registered_visitor WHERE email=?";
		Visitor visitor = null;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				if (set.getString(9).equals(password)) {
					System.out.println("LogIn Successful");
					visitor = new Visitor();
					visitor.setId(set.getInt(1));
					visitor.setName(set.getString(2));
					visitor.setEmail(set.getString(3));
					visitor.setPhone(set.getString(4));
					visitor.setGender(set.getString(5));
					visitor.setDob(set.getDate(6));
					visitor.setAge(set.getInt(7));
					visitor.setAddress(set.getString(8));
					visitor.setPassword(set.getString(9));
				} else {
					System.out.println("LogIn Failed");
				}
			} else {
				System.out.println("No User found having this email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visitor;
	}

}
