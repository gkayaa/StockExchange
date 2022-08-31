package com.example.evaexchange.dto;

import com.example.evaexchange.model.Share;
import com.example.evaexchange.model.User;
import com.sun.istack.NotNull;

public class ShareDTO 
{
	/*
	 * 
	 * 
	 * DATA TRANSFER OBJECT CLASS FOR SHARE SHARES.
	 * PROVIDES NOT TO TRANSFER DATABASE INFORMATION EVERY TIME IT IS NECESSARY. MINIMIZES THE READ-WRITE OPERATIONS ON THE DB
	 * 
	 * 
	 */
	
	@NotNull
	private int shareId;
	
	@NotNull
	private String shareName;
	
	@NotNull
	private double shareRate;

	public int getShareId() {
		return shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public double getShareRate() {
		return shareRate;
	}

	public void setShareRate(double shareRate) {
		this.shareRate = shareRate;
	}

	public static ShareDTO fromShare(Share share){
		ShareDTO shareInfoDTO = new ShareDTO();

		shareInfoDTO.setShareId(share.getShareId());
		shareInfoDTO.setShareName(share.getShareName());
		shareInfoDTO.setShareRate(share.getShareRate().doubleValue());

        return shareInfoDTO;
    }
	
}
