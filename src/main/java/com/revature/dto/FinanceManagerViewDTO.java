package com.revature.dto;

import java.sql.Blob;
import java.sql.Timestamp;

public class FinanceManagerViewDTO {

	private int userId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	
	private ReimbViewDTO reimbViewDTO;
	
	public FinanceManagerViewDTO(int userId, String username, String firstname, String lastname, String email,
			String role, ReimbViewDTO reimbViewDTO) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.reimbViewDTO = reimbViewDTO;
	}
	
	
	public FinanceManagerViewDTO() {
		super();
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ReimbViewDTO getReimbViewDTO() {
		return reimbViewDTO;
	}

	public void setReimbViewDTO(ReimbViewDTO reimbViewDTO) {
		this.reimbViewDTO = reimbViewDTO;
	}


	
	
	
	
}
