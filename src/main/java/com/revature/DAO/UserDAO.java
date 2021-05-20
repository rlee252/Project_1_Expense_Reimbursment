package com.revature.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.AddUserDTO;
import com.revature.dto.LoginDTO;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.util.ConnectionUtil;
import com.revature.util.SessionUtility;

public class UserDAO {
	Session session = SessionUtility.getSessionFactory().openSession();

	public User getUserByUsernameAndPassword(LoginDTO loginDTO) throws  ClientNotFoundException, DatabaseException, SQLException, NoSuchAlgorithmException {

		try (Session session = SessionUtility.getSessionFactory().openSession()) {
			 String userTest = loginDTO.getUsername();
			 String password = loginDTO.getPassword();
			 
			
		        
			    MessageDigest md = MessageDigest.getInstance("MD5");
			    md.update(password.getBytes());
			    byte[] digest = md.digest();
			    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			 
			 
			 User username  = (User) session.createQuery("FROM User u WHERE u.username = ?1 AND u.password = ?2 ")
										   .setParameter(1, userTest)
										   .setParameter(2, myHash)
										   .getSingleResult();
			 System.out.println(username);
			 

				return username;
			
			
		} catch (NoResultException e) {
		throw new ClientNotFoundException("Client with username/password was not found.");
		}
	}

	public User addUser(AddUserDTO addUserDTO) throws NoSuchAlgorithmException {
		Transaction tx1 = session.beginTransaction();
	
		
		String username = addUserDTO.getUsername();
		String password = addUserDTO.getPassword();
		String firstname = addUserDTO.getFirstname();
		String lastname = addUserDTO.getLastname();
		String email = addUserDTO.getEmail();
		String role = addUserDTO.getRole();
		
		
        
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(password.getBytes());
	    byte[] digest = md.digest();
	    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		UserRoles userrole = new UserRoles(0, role);
		session.save(userrole);
		
		User userAdd = new User(0,userrole,username,myHash,firstname,lastname,email);
		session.save(userAdd);
		tx1.commit();
		return userAdd;
		

	}

	public Object login(Object argThat) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
