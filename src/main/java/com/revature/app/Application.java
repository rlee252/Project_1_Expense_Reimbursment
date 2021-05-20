package com.revature.app;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.Controller;
import com.revature.controller.ExceptionMapper;
import com.revature.controller.LoginController;
import com.revature.controller.ReimbController;
import com.revature.controller.StaticFileController;
import com.revature.controller.UserController;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.SessionUtility;

import io.javalin.Javalin;

public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		Javalin app = Javalin.create((config)->{
			config.addStaticFiles("static");
		});
		
		mapControllers(app, new LoginController(), new ExceptionMapper(), new StaticFileController(), new ReimbController(), new UserController());
		
		//See if database is connected
//		try {
//			Connection connection =  ConnectionUtil.getConnection();
//			System.out.println(connection);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
	
		
//		Transaction tx = session.beginTransaction();
//		Reimbursement reim = new Reimbursement(0,null,null,null,null,2450, "took a cab",null,null);
//		session.save(reim);
//		//reim.setUser(user); // I can actually set my reim to this particular user
//		tx.commit();
		
		app.start(7000);
	}
	
	private static void mapControllers(Javalin app, Controller... controllers) {
		for (Controller c: controllers) {
			c.mapEndpoints(app);
		}
	}
	
	
}
