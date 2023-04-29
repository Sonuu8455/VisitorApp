package com.saroj.k2.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionGiver {
	public static Connection getCreatedConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/k2_m10";
			FileInputStream stream = new FileInputStream("src\\main\\resources\\mydbinfo.properties");
			Properties pro = new Properties();
			pro.load(stream);
			con = DriverManager.getConnection(url, pro);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
