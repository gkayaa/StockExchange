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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio")
public class Portfolio
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identifier for each portfolio
	private int portfolioId;
		
	@OneToOne(targetEntity = User.class, cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY) //Each user has a portfolio and each portfolio belongs to a single user (1-to-1 Relationship)
	@JoinColumn(nullable = false, unique = true)
    private User user;
	
	@ManyToMany(targetEntity = OwnedShare.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL) //Each portfolio includes more than one owned shares and also each owned shares might be in different portfolios. (M-to-M) Relationship)
    @JoinTable(
            name = "ownedshare_portfolio",
            joinColumns = @JoinColumn(name= "portfolioId" ),
            inverseJoinColumns = @JoinColumn(name = "ownedShareId")
    )
    private List<OwnedShare> ownedShares;
	
	public Portfolio() {
		
	}
	
	public Portfolio(User user, List<OwnedShare> ownedShares) {
		this.user = user;
		this.ownedShares = ownedShares;
	}
	
	public int getPortEntityId() {
		return portfolioId;
	}

	public void setPortEntityId(int portEntityId) {
		this.portfolioId = portEntityId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public List<OwnedShare> getOwnedShares() {
		return ownedShares;
	}

	public void setOwnedShares(List<OwnedShare> ownedShares) {
		this.ownedShares = ownedShares;
	}

}
