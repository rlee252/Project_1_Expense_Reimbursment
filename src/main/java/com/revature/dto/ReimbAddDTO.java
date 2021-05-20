package com.revature.dto;

import java.sql.Blob;
import java.sql.Timestamp;

import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbAddDTO {
	
	private String reimbType;
	private int reimbAmount;
	private String description;
	private Blob receipt;
	
	public ReimbAddDTO() {
		super();
		
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	public int getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	
	
	
}
