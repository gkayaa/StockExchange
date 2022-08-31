package com.example.evaexchange.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OwnedShare {
	/*
	 
	 This class was designed to keep the shares owned by each user. Portfolios are the generalized name and each user has a portfolio. Each portfolio has different shares owned and these are kept in the OwnedShare table
	 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identifier for owned shares
	private Long ownedShareId;
	
	/*Each share might be in different portfolios/owned shares lists. 
	  And an owned share entity might have the same share for once. Therefore, the relationship is 1-to-1*/
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Share.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE}) 
	@JoinColumn(nullable = false, referencedColumnName = "shareId")
	private Share share;
	
	@Column(nullable = false)
	private int quantity;

	public OwnedShare() {
		
	}
	
	public OwnedShare(Share share, int quantity) {
		super();
		this.share = share;
		this.quantity = quantity;
	}


	public Long getOwnedShareId() {
		return ownedShareId;
	}

	public void setOwnedShareId(Long ownedShareId) {
		this.ownedShareId = ownedShareId;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return ownedShareId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OwnedShare) {
			OwnedShare that = (OwnedShare) obj;
			return that.ownedShareId == this.ownedShareId;
		} else {
			return false;
		}
	
	
	}
	
}
