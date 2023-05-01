package com.saroj.k2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(AppConstants.DRIVER_CLASS);
			con = DriverManager.getConnection(AppConstants.URL,AppConstants.USER_NAME,AppConstants.PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
