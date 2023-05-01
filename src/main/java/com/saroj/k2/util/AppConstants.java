package com.saroj.k2.util;

public interface AppConstants {

	String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/k2_m10";
	String USER_NAME = "root";
	String PASSWORD = "root";
	
	String registeredVisitorInsertQuery = "INSERT INTO registered_visitor VALUES (?,?,?,?,?,?,?,?,?)";
	
	String validVisitorInsertQuery = "INSERT INTO valid_visitor VALUES (?,?,?,?,?,?,?,?,?)";
	
	String returnStmInsert=" data registered";
}
