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
import com.saroj.k2.util.AppConstants;
import com.saroj.k2.util.ConnectionUtil;

public class VisitorDAOImp implements VisitorDAO {

	@Override
	public String saveRegisteredVisitor(Visitor visitor) {
		Connection con = ConnectionUtil.getConnection();
//		String registeredVisitorInsertQuery = "INSERT INTO registered_visitor VALUES (?,?,?,?,?,?,?,?,?)";
		int res1=0;
		try {
			PreparedStatement ps1 = con.prepareStatement(AppConstants.registeredVisitorInsertQuery);
			ps1.setInt(1, visitor.getId());
			ps1.setString(2, visitor.getName());
			ps1.setString(3, visitor.getEmail());
			ps1.setString(4, visitor.getPhone());
			ps1.setString(5, visitor.getGender());
			ps1.setDate(6, visitor.getDob());
			ps1.setInt(7, visitor.getAge());
			ps1.setString(8, visitor.getAddress());
			ps1.setString(9, visitor.getPassword());
			
			res1 = ps1.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res1+AppConstants.returnStmInsert;
	}

	@Override
	public String saveValidVisitor(Visitor visitor) {
		Connection con = ConnectionUtil.getConnection();
//		String validVisitorInsertQuery = "INSERT INTO valid_visitor VALUES (?,?,?,?,?,?,?,?,?)";
		int res2=0;
		try {
			PreparedStatement ps2 = con.prepareStatement(AppConstants.validVisitorInsertQuery);
			ps2.setInt(1, visitor.getId());
			ps2.setString(2, visitor.getName());
			ps2.setString(3, visitor.getEmail());
			ps2.setString(4, visitor.getPhone());
			ps2.setString(5, visitor.getGender());
			ps2.setDate(6, visitor.getDob());
			ps2.setInt(7, visitor.getAge());
			ps2.setString(8, visitor.getAddress());
			ps2.setString(9, visitor.getPassword());
			
			res2 = ps2.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res2+AppConstants.returnStmInsert;
	}

	@Override
	public String updateRegisteredVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateValidVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String updateVisitor(Visitor visitor) {
		if (visitor.getEmail() == null) {
			return "Enter a email to update your data";
		}
		Connection con = ConnectionUtil.getConnection();
		String updateAll = "UPDATE registered_visitor SET name=?, phone=?, address=?, password=? WHERE email=?";
		String updateName = "UPDATE registered_visitor SET name=? WHERE email=?";
		String updatePhone = "UPDATE registered_visitor SET phone=? WHERE email=?";
		String updateAddress = "UPDATE registered_visitor SET address=? WHERE email=?";
		String updatePassword = "UPDATE registered_visitor SET password=? WHERE email=?";

		String query = "SELECT * FROM registered_visitor WHERE email=?";
		String updateAll1 = "UPDATE valid_visitor SET name=?, phone=?, address=?, password=? WHERE email=?";
		String updateName1 = "UPDATE valid_visitor SET name=? WHERE email=?";
		String updatePhone1 = "UPDATE valid_visitor SET phone=? WHERE email=?";
		String updateAddress1 = "UPDATE valid_visitor SET address=? WHERE email=?";
		String updatePassword1 = "UPDATE valid_visitor SET password=? WHERE email=?";
		String ret = "cannot update this due to some problem";
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM registered_visitor WHERE email=?");
			statement.setString(1, visitor.getEmail());
			ResultSet set2 = statement.executeQuery();
			if (!set2.next()) {
				return "unregistered user: cant not find data having this email";
			}

			if (visitor.getName() != null && visitor.getPhone() != null && visitor.getAddress() != null
					&& visitor.getPassword() != null) {
				PreparedStatement psAll = con.prepareStatement(updateAll);
				psAll.setString(1, visitor.getName());
				psAll.setString(2, visitor.getPhone());
				psAll.setString(3, visitor.getAddress());
				psAll.setString(4, visitor.getPassword());
				psAll.setString(5, visitor.getEmail());
				int res1 = psAll.executeUpdate();
				ret = res1 + " rows updated. name, phone, address and password updated";
			} else if (visitor.getName() != null) {
				PreparedStatement psName = con.prepareStatement(updateName);
				psName.setString(1, visitor.getName());
				psName.setString(2, visitor.getEmail());
				int res2 = psName.executeUpdate();
				ret = res2 + " rows updated. Name updated";
			} else if (visitor.getPhone() != null) {
				PreparedStatement psPhone = con.prepareStatement(updatePhone);
				psPhone.setString(1, visitor.getPhone());
				psPhone.setString(2, visitor.getEmail());
				int res3 = psPhone.executeUpdate();
				ret = res3 + " rows updated. phone updated";
			} else if (visitor.getAddress() != null) {
				PreparedStatement psAddress = con.prepareStatement(updateAddress);
				psAddress.setString(1, visitor.getAddress());
				psAddress.setString(2, visitor.getEmail());
				int res4 = psAddress.executeUpdate();
				ret = res4 + " rows updated. Address updated.";
			} else if (visitor.getPassword() != null) {
				PreparedStatement psPassword = con.prepareStatement(updatePassword);
				psPassword.setString(1, visitor.getPassword());
				psPassword.setString(2, visitor.getEmail());
				int res5 = psPassword.executeUpdate();
				ret = res5 + " rows updated. Password updated.";
			} else {
				ret = "this data can not be changed";
			}
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, visitor.getEmail());
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				if (set.getInt(7) >= 18) {
					if (visitor.getName() != null && visitor.getPhone() != null && visitor.getAddress() != null
							&& visitor.getPassword() != null) {
						PreparedStatement psAll = con.prepareStatement(updateAll1);
						psAll.setString(1, visitor.getName());
						psAll.setString(2, visitor.getPhone());
						psAll.setString(3, visitor.getAddress());
						psAll.setString(4, visitor.getPassword());
						psAll.setString(5, visitor.getEmail());
						psAll.executeUpdate();
					} else if (visitor.getName() != null) {
						PreparedStatement psName = con.prepareStatement(updateName1);
						psName.setString(1, visitor.getName());
						psName.setString(2, visitor.getEmail());
						psName.executeUpdate();
					} else if (visitor.getPhone() != null) {
						PreparedStatement psPhone = con.prepareStatement(updatePhone1);
						psPhone.setString(1, visitor.getPhone());
						psPhone.setString(2, visitor.getEmail());
						psPhone.executeUpdate();
					} else if (visitor.getAddress() != null) {
						PreparedStatement psAddress = con.prepareStatement(updateAddress1);
						psAddress.setString(1, visitor.getAddress());
						psAddress.setString(2, visitor.getEmail());
						psAddress.executeUpdate();
					} else if (visitor.getPassword() != null) {
						PreparedStatement psPassword = con.prepareStatement(updatePassword1);
						psPassword.setString(1, visitor.getPassword());
						psPassword.setString(2, visitor.getEmail());
						psPassword.executeUpdate();
					}
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public Visitor getVisitorById(int id) {
		Connection con = ConnectionUtil.getConnection();
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

	@Override
	public Visitor deleteVisitorById(int id) {
		Connection con = ConnectionUtil.getConnection();
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

	@Override
	public List<Visitor> getAllRegisteredVisitor() {
		List<Visitor> list = null;
		Connection con = ConnectionUtil.getConnection();
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

	@Override
	public List<Visitor> getAllValidVisitor() {
		List<Visitor> list = null;
		Connection con = ConnectionUtil.getConnection();
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

	@Override
	public Visitor visitorLogin(String email, String password) {
		Connection con = ConnectionUtil.getConnection();
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
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visitor;
	}

}
