package com.saroj.k2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saroj.k2.DTO.Visitor;
import com.saroj.k2.util.AppConstants;
import com.saroj.k2.util.ConnectionUtil;

public class VisitorDAOImp implements VisitorDAO {

	@Override
	public String saveRegisteredVisitor(Visitor visitor) {
		Connection con = ConnectionUtil.getConnection();
		int res1 = 0;
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
		return res1 + AppConstants.returnStmInsert;
	}

	@Override
	public String saveValidVisitor(Visitor visitor) {
		Connection con = ConnectionUtil.getConnection();
		int res2 = 0;
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
		return res2 + AppConstants.returnStmInsert;
	}

	@Override
	public String updateRegisteredVisitor(Visitor visitor) {
		String msg=AppConstants.retNull;
		if (visitor.getEmail() == null) {
			return "Enter a email to update your data";
		}
		if (visitor.getName() != null && visitor.getPhone() != null && visitor.getAddress() != null
				&& visitor.getPassword() != null) {
			msg = updateAll(visitor, AppConstants.updateAllRegistered);
		}else if (visitor.getName()!=null) {
			msg=updateName(visitor, AppConstants.updateNameRegistered);
		}else if (visitor.getPhone()!=null) {
			msg=updatePhone(visitor, AppConstants.updatePhoneRegistered);
		}else if (visitor.getAddress()!=null) {
			msg=updateAddress(visitor, AppConstants.updateAddressRegistered);
		}else if (visitor.getPassword()!=null) {
			msg=updatePassword(visitor, AppConstants.updatePasswordRegistered);
		}
		return msg;
	}

	@Override
	public String updateValidVisitor(Visitor visitor) {
		String msg=AppConstants.retNull;
		if (visitor.getEmail() == null) {
			return "Enter a email to update your data";
		}
		if (visitor.getName() != null && visitor.getPhone() != null && visitor.getAddress() != null
				&& visitor.getPassword() != null) {
			msg = updateAll(visitor, AppConstants.updateAllValid);
		}else if (visitor.getName()!=null) {
			msg=updateName(visitor, AppConstants.updateNameValid);
		}else if (visitor.getPhone()!=null) {
			msg=updatePhone(visitor, AppConstants.updatePhoneValid);
		}else if (visitor.getAddress()!=null) {
			msg=updateAddress(visitor, AppConstants.updateAddressValid);
		}else if (visitor.getPassword()!=null) {
			msg=updatePassword(visitor, AppConstants.updatePasswordValid);
		}
		return msg;
	}

	private String updateAll(Visitor visitor, String query) {
		Connection con = ConnectionUtil.getConnection();
		int res = 0;
		try {
			PreparedStatement psAll = con.prepareStatement(query);
			psAll.setString(1, visitor.getName());
			psAll.setString(2, visitor.getPhone());
			psAll.setString(3, visitor.getAddress());
			psAll.setString(4, visitor.getPassword());
			psAll.setString(5, visitor.getEmail());
			res = psAll.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res + AppConstants.returnStmUpdate;
	}

	private String updateName(Visitor visitor, String query) {
		Connection con = ConnectionUtil.getConnection();
		int res = 0;
		try {
			PreparedStatement psName = con.prepareStatement(query);
			psName.setString(1, visitor.getName());
			psName.setString(2, visitor.getEmail());
			res = psName.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res + AppConstants.returnStmUpdate;
	}

	private String updatePhone(Visitor visitor, String query) {
		Connection con = ConnectionUtil.getConnection();
		int res = 0;
		try {
			PreparedStatement psPhone = con.prepareStatement(query);
			psPhone.setString(1, visitor.getPhone());
			psPhone.setString(2, visitor.getEmail());
			res = psPhone.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res + AppConstants.returnStmUpdate;
	}

	private String updateAddress(Visitor visitor, String query) {
		Connection con = ConnectionUtil.getConnection();
		int res = 0;
		try {
			PreparedStatement psAddress = con.prepareStatement(query);
			psAddress.setString(1, visitor.getAddress());
			psAddress.setString(2, visitor.getEmail());
			res = psAddress.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res + AppConstants.returnStmUpdate;
	}

	private String updatePassword(Visitor visitor, String query) {
		Connection con = ConnectionUtil.getConnection();
		int res = 0;
		try {
			PreparedStatement psPassword = con.prepareStatement(query);
			psPassword.setString(1, visitor.getPassword());
			psPassword.setString(2, visitor.getEmail());
			res = psPassword.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res + AppConstants.returnStmUpdate;
	}

	@Override
	public Visitor getVisitorById(int id) {
		Connection con = ConnectionUtil.getConnection();
		Visitor visitor = null;
		try {
			PreparedStatement ps = con.prepareStatement(AppConstants.getVisitorById);
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
	public Visitor getVisitorByEmail(String email) {
		Connection con = ConnectionUtil.getConnection();
		String query = "SELECT * FROM registered_visitor WHERE email=?";
		Visitor visitor = null;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
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
