package com.revature.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.revature.DAO.UserDAO;

import com.revature.dto.LoginDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.exception.LoginException;
import com.revature.model.User;

public class LoginService {
	
	private UserDAO userDAO;
	
	public LoginService() {
		this.userDAO = new UserDAO();
	}
	
	//this one is for mockito
	public LoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	public User login(LoginDTO loginDTO) throws BadParameterException, LoginException, ClientNotFoundException, DatabaseException, SQLException, NoSuchAlgorithmException {
		//Do some checking for blank username, blank password
		if(loginDTO.getUsername().trim().equals("") || loginDTO.getPassword().trim().equals("")){
			throw new BadParameterException("cannot have blank username and/or password");
		}
		
		User user = userDAO.getUserByUsernameAndPassword(loginDTO);
		if(user == null) {
			throw new LoginException("User was not able to login with the supplied username and password");
		}
		return user;
		
		
	}
}
