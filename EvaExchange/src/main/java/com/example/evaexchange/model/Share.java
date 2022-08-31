package com.example.evaexchange.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "share")
public class Share 
{
	@Id
	@Column(name="shareId")
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identifier for each share
	private int shareId;
	
	@Column(nullable = false, length = 3) //Must be the length of 3 - The name of the share
	private String shareName;
	
	@Column(nullable = false, precision = 10, scale = 2) //2 decimal digits rule - e.g. the value 1.349 will be rounded to 1.35 
	private BigDecimal shareRate; //BigDecimal type was necessary to implement the 2 decimal digits rule. It is not valid for the type Double
		
	public Share() {
		
	}
	
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

	public BigDecimal getShareRate() {
		return shareRate;
	}

	public void setShareRate(BigDecimal shareRate) {
		this.shareRate = shareRate;
	}

	public Share(String shareName, double shareRate)
	{
		this.shareName = shareName;
		this.shareRate = BigDecimal.valueOf(shareRate);
	}

}
