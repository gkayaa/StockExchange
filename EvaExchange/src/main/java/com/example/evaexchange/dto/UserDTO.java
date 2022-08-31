package com.example.evaexchange.dto;

import com.example.evaexchange.model.User;
import com.sun.istack.NotNull;

public class UserDTO
{
	/*
	 * 
	 * 
	 * DATA TRANSFER OBJECT CLASS FOR SHARE USERS.
	 * PROVIDES NOT TO TRANSFER DATABASE INFORMATION EVERY TIME IT IS NECESSARY. MINIMIZES THE READ-WRITE OPERATIONS ON THE DB
	 * 
	 * 
	 */
	
	@NotNull
	private int id;
	
	@NotNull
	private String userFirstName;
	
	@NotNull
	private String userLastName;

	@NotNull
	private double userBalance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}

	
	public static UserDTO fromUser(User user){
		UserDTO userInfoDTO = new UserDTO();

		userInfoDTO.setId(user.getId());
		userInfoDTO.setUserFirstName(user.getUserFirstName());
		userInfoDTO.setUserLastName(user.getUserLastName());
		userInfoDTO.setUserBalance(user.getUserBalance());

        return userInfoDTO;
    }

}
