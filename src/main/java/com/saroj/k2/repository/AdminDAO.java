package com.saroj.k2.repository;

import com.saroj.k2.DTO.Admin;

public interface AdminDAO {

	public String saveAdmin(Admin admin);

	public Admin adminLogin(String userName, String password);

}
