package com.revature.controller;

import java.util.List;

import com.revature.dto.FinanceManagerViewDTO;
import com.revature.dto.LoginDTO;
import com.revature.dto.ReimbAddDTO;
import com.revature.dto.ReimbViewDTO;
import com.revature.dto.StatusDTO;
import com.revature.dto.UpdateStatusDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbController implements Controller{

	private ReimbService reimbService;
	public ReimbController() {
		this.reimbService = new ReimbService();
	}
	
	
	
	private Handler addReimbHandler = ctx -> {
		User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		ReimbAddDTO  reimbAddDTO = ctx.bodyAsClass(ReimbAddDTO.class);
		Reimbursement reimb = reimbService.addReimb(reimbAddDTO, user);
		ctx.json(reimb);
		ctx.status(200);
	};
	
	private Handler getAllReimbHandler = ctx -> {
		//User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		List<Reimbursement> Reimbursement = reimbService.getAllReimb();
		ctx.json(Reimbursement);
		ctx.status(200);
	};
	private Handler getReimbHandler2 = ctx -> {
		User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		List<ReimbViewDTO> reimbViewDTO = reimbService.getReimb2(user);
		ctx.json(reimbViewDTO);
		ctx.status(200);
	};
	
	private Handler financeManagerViewHandler = ctx -> {
		
		List<FinanceManagerViewDTO> financeManagerViewDTO = reimbService.getFinanceManagerView();
		ctx.json(financeManagerViewDTO);
		ctx.status(200);
	};
	
private Handler updateStatusHandler = ctx -> {
	User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
	UpdateStatusDTO  updateStatusDTO = ctx.bodyAsClass(UpdateStatusDTO.class);
	Reimbursement reimb = reimbService.updateStatus(user,updateStatusDTO);
	
		ctx.json(user);
		ctx.status(200);
	};
	
	private Handler filterAcceptHandler = ctx -> {
		List<FinanceManagerViewDTO> financeViewDTO = reimbService.filterReimb();
		ctx.json(financeViewDTO);
			ctx.status(200);
		};
		
	private Handler filterDeclineHandler = ctx -> {
			List<FinanceManagerViewDTO> financeViewDTO = reimbService.filterDeclineReimb();
			ctx.json(financeViewDTO);
				ctx.status(200);
		};
	private Handler filterPendingHandler = ctx -> {
			List<FinanceManagerViewDTO> financeViewDTO = reimbService.filterPendingReimb();
			ctx.json(financeViewDTO);
				ctx.status(200);
			};
	@Override
	public void mapEndpoints(Javalin app) {
		
		app.post("/Reimb", addReimbHandler);
		app.get("/getAllReimb", getAllReimbHandler);
		app.get("/ReimbV2", getReimbHandler2);
		app.get("/financeManagerView", financeManagerViewHandler);
		app.post("/updateStatus", updateStatusHandler);
		app.get("/filterAccept", filterAcceptHandler);
		app.get("/filterDecline", filterDeclineHandler);
		app.get("/filterPending", filterPendingHandler);
	}

}
