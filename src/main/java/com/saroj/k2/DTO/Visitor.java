package com.saroj.k2.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String gender;
	private Date dob;
	private Integer age;
	private String address;
	private String password;
}
