package com.example.evaexchange.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User 
{
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identifier of the user model
	private int id;
	
	@Column(nullable = false)
	private String userFirstName;
	
	@Column(nullable = false)
	private String userLastName;
	
	@Column(nullable = false)
	private double userBalance;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Portfolio.class) //Each portfolio is owned by a single user. And also each user has only one portfolio (1-to-1 Relationship)
	private Portfolio portfolio;
	
	public User() {
		
	}
	
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


	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public User(String userFirstName, String userLastName, double userBalance)
	{
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userBalance = userBalance;
	}
	
	@Override
	public String toString() {
		
		return "ID: " + id + " Name: " + userFirstName + " Surname: " + userLastName;
		
	}
	
}
