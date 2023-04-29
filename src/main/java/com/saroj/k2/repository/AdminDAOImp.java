package com.saroj.k2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.saroj.k2.DTO.Admin;

public class AdminDAOImp implements AdminDAO {

	@Override
	public String saveAdmin(Admin admin) {
		Connection con = ConnectionGiver.getCreatedConnection();
		String query="INSERT INTO admin VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, admin.getId());
			ps.setString(2, admin.getUserName());
			ps.setString(3, admin.getPassword());
			int res = ps.executeUpdate();
			con.close();
			return res+" rows inserted";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "no rows inserted";
	}

	@Override
	public Admin adminLogin(String userName, String password) {
		return null;
	}

}
