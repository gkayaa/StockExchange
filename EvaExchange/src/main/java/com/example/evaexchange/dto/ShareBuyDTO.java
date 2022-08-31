package com.example.evaexchange.dto;

import javax.validation.constraints.NotNull;

public class ShareBuyDTO {
	/*
	 * 
	 * 
	 * DATA TRANSFER OBJECT CLASS FOR SHARE TRANSACTIONS.
	 * PROVIDES NOT TO TRANSFER DATABASE INFORMATION EVERY TIME IT IS NECESSARY. MINIMIZES THE READ-WRITE OPERATIONS ON THE DB
	 * 
	 * 
	 */

	@NotNull
	private Integer userId;
	@NotNull
	private int shareId;
	@NotNull
	private int quantity;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public int getShareId() {
		return shareId;
	}
	public void setShareId(int shareId) {
		this.shareId = shareId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
