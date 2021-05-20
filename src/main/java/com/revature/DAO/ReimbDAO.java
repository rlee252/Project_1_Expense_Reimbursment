package com.revature.DAO;

import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.FinanceManagerViewDTO;
import com.revature.dto.ReimbAddDTO;
import com.revature.dto.ReimbViewDTO;
import com.revature.dto.StatusDTO;
import com.revature.dto.UpdateStatusDTO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.util.SessionUtility;

public class ReimbDAO {
	
	

	public Reimbursement addReimb(ReimbAddDTO reimbAddDTO, User user) {
		Session session = SessionUtility.getSessionFactory().openSession();
		Transaction tx4 = session.beginTransaction();
		
		ReimbursementStatus reimStat = new ReimbursementStatus(0,"pending");
		session.save(reimStat);
		
		String reimbType = reimbAddDTO.getReimbType();
		ReimbursementType reimType = new ReimbursementType(0, reimbType);
		session.save(reimType);
		
		int amount = reimbAddDTO.getReimbAmount();
		String description = reimbAddDTO.getDescription();
		Blob receipt = reimbAddDTO.getReceipt();
		
		LocalDate myObj = LocalDate.now(); // Create a date object
	    LocalTime myObj2 = LocalTime.now();
	    String created = myObj.toString() + myObj2.toString();
		Reimbursement r = new Reimbursement(0,reimStat,reimType,user,null,amount,description,created,"",null);
		session.save(r);
		tx4.commit();
		session.close();
		return r;
	}

	public List<Reimbursement> getAllReimb() {
		Session session = SessionUtility.getSessionFactory().openSession();
		
		List<Reimbursement> reimb = session.createQuery("FROM Reimbursement r")
										   .getResultList();
		session.close();
		return reimb;
	}

	public List<ReimbViewDTO> getReimb2(User user) {
		int userId = user.getId();
		Session session = SessionUtility.getSessionFactory().openSession();
		
		List<Reimbursement> reimb = session.createQuery("SELECT r FROM Reimbursement r JOIN r.reimbAuthor u WHERE u.id = ?1")
				   .setParameter(1, userId)
				   .getResultList();
		List<ReimbViewDTO> reimbViewDTO = new ArrayList<>();
			
			for (Reimbursement r: reimb) {
				int id = ((Reimbursement) r).getReimbId();
				
				String description =  ((Reimbursement) r).getDescription();
				
				ReimbursementStatus reimbStat = ((Reimbursement) r).getReimbStatus();
				String status = reimbStat.getReimbStatus();
				
				ReimbursementType reimbType = ((Reimbursement) r).getReimbType();
				String type = reimbType.getReimbType();
				
				int amount = ((Reimbursement) r).getReimbAmount();
				
				String created = ((Reimbursement) r).getCreatedOn();
				String update = ((Reimbursement) r).getUpdatedOn();
				Blob reimbReceipt =  ((Reimbursement) r).getReceipt();
				
				String resolver = null ;
				if (((Reimbursement) r).getReimbResolver() == null) {
					resolver = "unresolved";
				} else {
					resolver = String.valueOf(  ((Reimbursement) r).getReimbResolver().getId());
				}
				reimbViewDTO.add(new ReimbViewDTO(id, status,type,amount, description,created,update,null,resolver));
		
				}
			session.close();
			return reimbViewDTO;
	}

	public List<FinanceManagerViewDTO> getFinanceManagerView() {
Session session = SessionUtility.getSessionFactory().openSession();
		
		List<Reimbursement> reimb = session.createQuery("FROM Reimbursement r")
										   .getResultList();
		
		List<FinanceManagerViewDTO> financeManagerViewDTO = new ArrayList<>();
		
		for (Reimbursement r: reimb) {
			
			int userId = ((Reimbursement) r).getReimbAuthor().getId();
			String username = ((Reimbursement) r).getReimbAuthor().getUsername();
			String firstname = ((Reimbursement) r).getReimbAuthor().getFirstName();
			String lastname = ((Reimbursement) r).getReimbAuthor().getLastName();
			String email = ((Reimbursement) r).getReimbAuthor().getEmail();
			String role = ((Reimbursement) r).getReimbAuthor().getUserRole().getUserRole();
			
			//=========
			int id = ((Reimbursement) r).getReimbId();
			String description =  ((Reimbursement) r).getDescription();
			ReimbursementStatus reimbStat = ((Reimbursement) r).getReimbStatus();
			String status = reimbStat.getReimbStatus();	
			ReimbursementType reimbType = ((Reimbursement) r).getReimbType();
			String type = reimbType.getReimbType();
			int amount = ((Reimbursement) r).getReimbAmount();
			String created = ((Reimbursement) r).getCreatedOn();
			String updated = ((Reimbursement) r).getUpdatedOn();
			Blob reimbReceipt =  ((Reimbursement) r).getReceipt();
			String resolver = null ;
			if (((Reimbursement) r).getReimbResolver() == null) {
				resolver = "unresolved";
			} else {
				resolver = String.valueOf(  ((Reimbursement) r).getReimbResolver().getId());
			}
			ReimbViewDTO reimbView = new ReimbViewDTO(id, status,type,amount, description,created,updated,null,resolver);
			
			financeManagerViewDTO.add(new FinanceManagerViewDTO(userId,username,firstname,lastname,email,role,reimbView));
			}
		session.close();
		return financeManagerViewDTO;
	}

	public Reimbursement updateStatus(User user, UpdateStatusDTO updateStatusDTO) {
		Session session = SessionUtility.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//int userId = user.getId();
		int reimbId = updateStatusDTO.getReimbId();
		String status = updateStatusDTO.getStatus();
		
		LocalDate myObj = LocalDate.now(); // Create a date object
	    LocalTime myObj2 = LocalTime.now();
	    String updated = myObj.toString() + myObj2.toString();
	    
		Reimbursement reimb = session.get(Reimbursement.class,reimbId);
		reimb.setReimbResolver(user);
		reimb.setUpdatedOn(updated);
		session.update(reimb);
		
		int reimbStatId = reimb.getReimbStatus().getReimbStatusId();
		ReimbursementStatus reimbStat = session.get(ReimbursementStatus.class,reimbStatId);
		reimbStat.setReimbStatus(status);
		tx.commit();
		session.close();
		return reimb;
	}

	public List<FinanceManagerViewDTO> filterReimb() {
		
			Session session = SessionUtility.getSessionFactory().openSession();
			String status1 = "accepted";
			List<Reimbursement> reimb = session.createQuery("SELECT r FROM Reimbursement r JOIN r.reimbStatus s WHERE s.reimbStatus = ?1")
			.setParameter(1, status1)
			.getResultList();
			List<FinanceManagerViewDTO> financeManagerViewDTO = new ArrayList<>();
			
			for (Reimbursement r: reimb) {
				
				int userId = ((Reimbursement) r).getReimbAuthor().getId();
				String username = ((Reimbursement) r).getReimbAuthor().getUsername();
				String firstname = ((Reimbursement) r).getReimbAuthor().getFirstName();
				String lastname = ((Reimbursement) r).getReimbAuthor().getLastName();
				String email = ((Reimbursement) r).getReimbAuthor().getEmail();
				String role = ((Reimbursement) r).getReimbAuthor().getUserRole().getUserRole();
				
				//=========
				int id = ((Reimbursement) r).getReimbId();
				String description =  ((Reimbursement) r).getDescription();
				ReimbursementStatus reimbStat = ((Reimbursement) r).getReimbStatus();
				String status = reimbStat.getReimbStatus();	
				ReimbursementType reimbType = ((Reimbursement) r).getReimbType();
				String type = reimbType.getReimbType();
				int amount = ((Reimbursement) r).getReimbAmount();
				String created = ((Reimbursement) r).getCreatedOn();
				String updated = ((Reimbursement) r).getUpdatedOn();
				Blob reimbReceipt =  ((Reimbursement) r).getReceipt();
				String resolver = null ;
				if (((Reimbursement) r).getReimbResolver() == null) {
					resolver = "unresolved";
				} else {
					resolver = String.valueOf(  ((Reimbursement) r).getReimbResolver().getId());
				}
				ReimbViewDTO reimbView = new ReimbViewDTO(id, status,type,amount, description,created,updated,null,resolver);
				
				financeManagerViewDTO.add(new FinanceManagerViewDTO(userId,username,firstname,lastname,email,role,reimbView));
				}
			session.close();
			return financeManagerViewDTO;			
		
		
		
		
	}

	public List<FinanceManagerViewDTO> filterDeclineReimb() {
		Session session = SessionUtility.getSessionFactory().openSession();
		String status1 = "declined";
		List<Reimbursement> reimb = session.createQuery("SELECT r FROM Reimbursement r JOIN r.reimbStatus s WHERE s.reimbStatus = ?1")
		.setParameter(1, status1)
		.getResultList();
		List<FinanceManagerViewDTO> financeManagerViewDTO = new ArrayList<>();
		
		for (Reimbursement r: reimb) {
			
			int userId = ((Reimbursement) r).getReimbAuthor().getId();
			String username = ((Reimbursement) r).getReimbAuthor().getUsername();
			String firstname = ((Reimbursement) r).getReimbAuthor().getFirstName();
			String lastname = ((Reimbursement) r).getReimbAuthor().getLastName();
			String email = ((Reimbursement) r).getReimbAuthor().getEmail();
			String role = ((Reimbursement) r).getReimbAuthor().getUserRole().getUserRole();
			
			//=========
			int id = ((Reimbursement) r).getReimbId();
			String description =  ((Reimbursement) r).getDescription();
			ReimbursementStatus reimbStat = ((Reimbursement) r).getReimbStatus();
			String status = reimbStat.getReimbStatus();	
			ReimbursementType reimbType = ((Reimbursement) r).getReimbType();
			String type = reimbType.getReimbType();
			int amount = ((Reimbursement) r).getReimbAmount();
			String created = ((Reimbursement) r).getCreatedOn();
			String updated = ((Reimbursement) r).getUpdatedOn();
			Blob reimbReceipt =  ((Reimbursement) r).getReceipt();
			String resolver = null ;
			if (((Reimbursement) r).getReimbResolver() == null) {
				resolver = "unresolved";
			} else {
				resolver = String.valueOf(  ((Reimbursement) r).getReimbResolver().getId());
			}
			ReimbViewDTO reimbView = new ReimbViewDTO(id, status,type,amount, description,created,updated,null,resolver);
			
			financeManagerViewDTO.add(new FinanceManagerViewDTO(userId,username,firstname,lastname,email,role,reimbView));
			}
		session.close();
		return financeManagerViewDTO;		
	}

	public List<FinanceManagerViewDTO> filterPendingReimb() {
		Session session = SessionUtility.getSessionFactory().openSession();
		String status1 = "pending";
		List<Reimbursement> reimb = session.createQuery("SELECT r FROM Reimbursement r JOIN r.reimbStatus s WHERE s.reimbStatus = ?1")
		.setParameter(1, status1)
		.getResultList();
		List<FinanceManagerViewDTO> financeManagerViewDTO = new ArrayList<>();
		
		for (Reimbursement r: reimb) {
			
			int userId = ((Reimbursement) r).getReimbAuthor().getId();
			String username = ((Reimbursement) r).getReimbAuthor().getUsername();
			String firstname = ((Reimbursement) r).getReimbAuthor().getFirstName();
			String lastname = ((Reimbursement) r).getReimbAuthor().getLastName();
			String email = ((Reimbursement) r).getReimbAuthor().getEmail();
			String role = ((Reimbursement) r).getReimbAuthor().getUserRole().getUserRole();
			
			//=========
			int id = ((Reimbursement) r).getReimbId();
			String description =  ((Reimbursement) r).getDescription();
			ReimbursementStatus reimbStat = ((Reimbursement) r).getReimbStatus();
			String status = reimbStat.getReimbStatus();	
			ReimbursementType reimbType = ((Reimbursement) r).getReimbType();
			String type = reimbType.getReimbType();
			int amount = ((Reimbursement) r).getReimbAmount();
			String created = ((Reimbursement) r).getCreatedOn();
			String updated = ((Reimbursement) r).getUpdatedOn();
			Blob reimbReceipt =  ((Reimbursement) r).getReceipt();
			String resolver = null ;
			if (((Reimbursement) r).getReimbResolver() == null) {
				resolver = "unresolved";
			} else {
				resolver = String.valueOf(  ((Reimbursement) r).getReimbResolver().getId());
			}
			ReimbViewDTO reimbView = new ReimbViewDTO(id, status,type,amount, description,created,updated,null,resolver);
			
			financeManagerViewDTO.add(new FinanceManagerViewDTO(userId,username,firstname,lastname,email,role,reimbView));
			}
		session.close();
		return financeManagerViewDTO;	
	}


}
