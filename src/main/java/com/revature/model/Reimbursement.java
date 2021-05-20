package com.revature.model;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	private int reimbId;
	
	
	
	@ManyToOne 
	@JoinColumn(name = "reimb_status_id")
	private ReimbursementStatus reimbStatus;
	
	@ManyToOne 
	@JoinColumn(name = "reimb_type_id")
	private ReimbursementType reimbType;
	
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private User reimbAuthor;
	
	@ManyToOne 
	@JoinColumn(name = "user_email")
	private User reimbResolver;
	
	@Column(name = "reimb_amount")
	private int reimbAmount;
	
	@Column(name = "reimb_description", length = 50)
	private String description;
	
	@Column(name = "reimb_timestamp_created")
	private String createdOn;
	
	@Column(name = "reimb_timestamp_udpated")
	private String updatedOn;
	
	@Column(name = "reimb_receipt")
	private Blob receipt;

	public Reimbursement() {
		super();
		
	}

	public Reimbursement(int reimbId, ReimbursementStatus reimbStatus, ReimbursementType reimbType, User reimbAuthor,
			User reimbResolver, int reimbAmount, String description, String createdOn, String updatedOn, Blob receipt) {
		super();
		this.reimbId = reimbId;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbAmount = reimbAmount;
		this.description = description;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.receipt = receipt;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public ReimbursementStatus getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(ReimbursementStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public ReimbursementType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbursementType reimbType) {
		this.reimbType = reimbType;
	}

	public User getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public User getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + reimbAmount;
		result = prime * result + ((reimbAuthor == null) ? 0 : reimbAuthor.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolver == null) ? 0 : reimbResolver.hashCode());
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((updatedOn == null) ? 0 : updatedOn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimbAmount != other.reimbAmount)
			return false;
		if (reimbAuthor == null) {
			if (other.reimbAuthor != null)
				return false;
		} else if (!reimbAuthor.equals(other.reimbAuthor))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolver == null) {
			if (other.reimbResolver != null)
				return false;
		} else if (!reimbResolver.equals(other.reimbResolver))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (updatedOn == null) {
			if (other.updatedOn != null)
				return false;
		} else if (!updatedOn.equals(other.updatedOn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType
				+ ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbAmount=" + reimbAmount
				+ ", description=" + description + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", receipt=" + receipt + "]";
	}

	
	
	
	
	
	
	
}


