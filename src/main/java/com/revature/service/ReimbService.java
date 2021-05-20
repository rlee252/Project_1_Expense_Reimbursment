package com.revature.service;

import java.util.List;

import com.revature.DAO.ReimbDAO;
import com.revature.dto.FinanceManagerViewDTO;
import com.revature.dto.ReimbAddDTO;
import com.revature.dto.ReimbViewDTO;
import com.revature.dto.StatusDTO;
import com.revature.dto.UpdateStatusDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbService {
	
	private ReimbDAO reimbDAO;

	public ReimbService() {
		this.reimbDAO = new ReimbDAO();

	}


	public Reimbursement addReimb(ReimbAddDTO reimbAddDTO, User user) {
		Reimbursement reim = reimbDAO.addReimb(reimbAddDTO, user);
		return reim;
	}

	public List<Reimbursement> getAllReimb() {
		return reimbDAO.getAllReimb();
	}

	public List<ReimbViewDTO> getReimb2(User user) {
		return reimbDAO.getReimb2(user);
	}

	public List<FinanceManagerViewDTO> getFinanceManagerView() {
		
		return reimbDAO.getFinanceManagerView();
	}

	public Reimbursement updateStatus(User user, UpdateStatusDTO updateStatusDTO) {
		return reimbDAO.updateStatus(user,updateStatusDTO);
		
	}



	public List<FinanceManagerViewDTO> filterReimb() {
		return reimbDAO.filterReimb();
		
	}



	public List<FinanceManagerViewDTO> filterDeclineReimb() {
		return reimbDAO.filterDeclineReimb();
		
	}



	public List<FinanceManagerViewDTO> filterPendingReimb() {
		return reimbDAO.filterPendingReimb();
	}




}
