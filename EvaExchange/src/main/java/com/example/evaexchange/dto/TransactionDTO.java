package com.example.evaexchange.dto;

import com.example.evaexchange.model.Share;
import com.example.evaexchange.model.Transaction;
import com.example.evaexchange.model.User;
import com.sun.istack.NotNull;

public class TransactionDTO 
{
	/*
	 * 
	 * 
	 * DATA TRANSFER OBJECT CLASS FOR SHARE TRANSACTIONS.
	 * PROVIDES NOT TO TRANSFER DATABASE INFORMATION EVERY TIME IT IS NECESSARY. MINIMIZES THE READ-WRITE OPERATIONS ON THE DB
	 * 
	 * 
	 */
	
	@NotNull
	private int transactionId;
	
	@NotNull
    private Share share;
	
	@NotNull
    private User user;

	@NotNull
	private int quantity;
	
	@NotNull
	private double transactionCost;
	
	@NotNull
	private double transactionRate;
	
	@NotNull
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public static TransactionDTO fromTransaction(Transaction transaction){
		TransactionDTO transactionInfoDTO = new TransactionDTO();

		transactionInfoDTO.setTransactionRate(transaction.getTransactionRate().doubleValue());
		transactionInfoDTO.setTransactionCost(transaction.getTransactionRate().doubleValue() * transaction.getTransactionQuantity());
		transactionInfoDTO.setTransactionId(transaction.getTransactionId());
		transactionInfoDTO.setUser(transaction.getUser());
		transactionInfoDTO.setShare(transaction.getShare());
		transactionInfoDTO.setQuantity(transaction.getTransactionQuantity());
		transactionInfoDTO.setTransactionType(transaction.getTransactionType());

        return transactionInfoDTO;
    }

	public double getTransactionCost() {
		return transactionCost;
	}

	public void setTransactionCost(double transactionCost) {
		this.transactionCost = transactionCost;
	}

	public double getTransactionRate() {
		return transactionRate;
	}

	public void setTransactionRate(double transactionRate) {
		this.transactionRate = transactionRate;
	}

}
