package com.saroj.k2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saroj.k2.DTO.Admin;

public class AdminDAOImp implements AdminDAO {

	@Override
	public String saveAdmin(Admin admin) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String query = "INSERT INTO admin VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, admin.getId());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getPassword());
			int res = ps.executeUpdate();
			con.close();
			return res + " rows inserted";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "no rows inserted";
	}

	@Override
	public Admin adminLogin(String userName, String password) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String query = "SELECT * FROM admin WHERE user_name=?";
		Admin admin = null;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				if (set.getString(3).equals(password)) {
					System.out.println("LogIn Successful");
					admin = new Admin();
					admin.setId(set.getInt(1));
					admin.setUserName(set.getString(2));
					admin.setPassword(set.getString(3));
				} else {
					System.out.println("LogIn Failed :( ");
				}
			} else {
				System.out.println("No user foung having this user_name");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
