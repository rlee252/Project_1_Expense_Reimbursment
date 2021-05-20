package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.model.User;
import com.revature.service.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController implements Controller {
	
	private LoginService loginService;
	public LoginController() {
		this.loginService = new LoginService();
	}
	
	private Handler loginHandler = (ctx) -> {
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
		User user = loginService.login(loginDTO);

		ctx.sessionAttribute("currentlyLoggedInUser", user);
		ctx.json(user);
		
	};
	
	private Handler currentUserHandler = (ctx)->{
		User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		if(user == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not currently logged in!");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
		ctx.json(user);
		}
	};
	
	private Handler logoutHandler = (ctx)-> {
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setMessage("User is logged out!");
		ctx.json(messageDTO);
		//Javalin sits on top of a lower level API, called Servlet API
		//So really behind the scenes all that is doing the HTTP processing are Servlets
		// we can actually access objects that belong to the Servlet API directly
		//such as HttpServletRequest and HttpServletResponse
		
		//so here. I'm using the HttpServletRequest object associated with this logout request
		//using the getSession method, then calling invalidate
		ctx.req.getSession().invalidate(); //invalidate's session, a way to logout
	};

	public void mapEndpoints(Javalin app) {
		 app.post("/login", loginHandler);
		 app.get("/current_user", currentUserHandler);
		 app.post("/logout", logoutHandler);
	}

}
