package com.example.evaexchange.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinColumnOrFormula;

@Entity
@Table(name = "transaction")
public class Transaction 
{
	/*
	 * Transaction class to keep the track of transaction operations performed by the users registered on the system. (Log in other words) 
	 */
	
	
	@Id
	@Column(name = "transactionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Transaction identifier
	private int transactionId;

	/*
	 * Each transaction entity has only one user and only one share (1-to-1 Relationship)
	 */
	
	@OneToOne(targetEntity = Share.class, cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "shareId", referencedColumnName = "shareId")
    private Share share;
	
	@OneToOne(targetEntity = User.class, cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "userId", referencedColumnName = "userId")
    private User user;
	
	@Column(name="transactionQuantity")
	private int transactionQuantity;
	
	@Column(name="transactionType")
	private String transactionType;
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTransactionQuantity() {
		return transactionQuantity;
	}

	public void setTransactionQuantity(int quantity) {
		this.transactionQuantity = quantity;
	}

	public BigDecimal getTransactionRate() {
		return share.getShareRate();
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public Transaction() {
		
	}
	
	public Transaction(String transactionType, Share share, User user, int transactionQuantity)
	{
		this.transactionType = transactionType;
		this.transactionQuantity = transactionQuantity;
		this.share = share;
		this.user = user;
	}
}
