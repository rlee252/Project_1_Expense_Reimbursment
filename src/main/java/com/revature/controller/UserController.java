package com.revature.controller;

import com.revature.dto.AddUserDTO;
import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {
	
	private UserService userService;
	public UserController() {
		this.userService = new UserService();
	}
	
	private Handler addUserHandler = (ctx)-> {
		AddUserDTO addUserDTO = ctx.bodyAsClass(AddUserDTO.class);
		 User user = UserService.addUser(addUserDTO);
		ctx.json("user has been added " + user);
		ctx.status(200);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
	  app.post("/signup",addUserHandler);
	}

}
