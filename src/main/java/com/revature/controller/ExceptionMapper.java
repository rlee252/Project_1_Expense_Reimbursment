package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.LoginException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionMapper implements Controller {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

	private ExceptionHandler<BadParameterException> badParameterException = (e, ctx) -> {
			ctx.status(400);
			logger.warn(e.getMessage());
	};

	private ExceptionHandler<LoginException> loginException = (e, ctx) -> {
			ctx.status(400);
			logger.warn(e.getMessage());
	};
	
	private ExceptionHandler<ClientNotFoundException> clientNotFoundException = ( e,ctx) ->{
			ctx.status(400);
			logger.warn(e.getMessage());
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(BadParameterException.class, badParameterException);
		app.exception(LoginException.class, loginException);
		app.exception(ClientNotFoundException.class, clientNotFoundException);
	}

}
