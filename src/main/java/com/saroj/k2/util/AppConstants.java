package com.saroj.k2.util;

public interface AppConstants {

	String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/k2_m10";
	String USER_NAME = "root";
	String PASSWORD = "root";
	
	String SECRET_KEY="s@#GDK^*<AD%()?>$#@JGAD)?@&";//This is used to encrypt or decrypt information.
	
	String registeredVisitorInsertQuery = "INSERT INTO registered_visitor VALUES (?,?,?,?,?,?,?,?,?)";
	
	String validVisitorInsertQuery = "INSERT INTO valid_visitor VALUES (?,?,?,?,?,?,?,?,?)";
	
	String updateAllRegistered = "UPDATE registered_visitor SET name=?, phone=?, address=?, password=? WHERE email=?";
	String updateAllValid = "UPDATE valid_visitor SET name=?, phone=?, address=?, password=? WHERE email=?";
	
	String updateNameRegistered="UPDATE registered_visitor SET name=? WHERE email=?";
	String updateNameValid="UPDATE valid_visitor SET name=? WHERE email=?";
	
	String updatePhoneRegistered="UPDATE registered_visitor SET phone=? WHERE email=?";
	String updatePhoneValid="UPDATE valid_visitor SET phone=? WHERE email=?";
	
	String updateAddressRegistered="UPDATE registered_visitor SET address=? WHERE email=?";
	String updateAddressValid="UPDATE valid_visitor SET address=? WHERE email=?";
	
	String updatePasswordRegistered="UPDATE registered_visitor SET password=? WHERE email=?";
	String updatePasswordValid="UPDATE valid_visitor SET password=? WHERE email=?";
	
	String getVisitorById="SELECT * FROM registered_visitor WHERE id=?";
	String getVisitorByEmail="SELECT * FROM registered_visitor WHERE email=?";
	
	String deleteRegisteredVisitorById="DELETE FROM registered_visitor WHERE id=?";
	String deleteValidVisitorById="DELETE FROM valid_visitor WHERE id=?";
	
	String deleteRegisteredVisitorByEmail="DELETE FROM registered_visitor WHERE email=?";
	String deleteValidVisitorByEmail="DELETE FROM valid_visitor WHERE email=?";
	
	String selectAllRegistered="SELECT * FROM registered_visitor";
	String selectAllValid="SELECT * FROM valid_visitor";
	
	String noEmailPresent="Enter a email to update your data";
	String diffEmailEntered="No data found associated with this email or email not registered";
	String returnStmInsert=" data registered";
	String returnStmUpdate=" row data updated";
	String retNull="No data updated";
}
