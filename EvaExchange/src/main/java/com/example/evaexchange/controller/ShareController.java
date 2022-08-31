package com.example.evaexchange.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaexchange.dto.ShareBuyDTO;
import com.example.evaexchange.dto.TransactionDTO;
import com.example.evaexchange.model.OwnedShare;
import com.example.evaexchange.model.Portfolio;
import com.example.evaexchange.model.Share;
import com.example.evaexchange.model.Transaction;
import com.example.evaexchange.model.User;
import com.example.evaexchange.service.ShareService;
import com.example.evaexchange.service.TransactionService;
import com.example.evaexchange.service.UserService;

@RestController
@RequestMapping("/rest/share")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ShareController {
	
	/*
	 * 
	 * 
	 * All the expected endpoints were implemented on this controller class
	 * 
	 * 
	 * 
	 */
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private TransactionService transactionService;

	private OwnedShare getOwnedShareFromShare(User user, Share share) //Get the owned share if it exists in the related user portfolio
	{
		for (OwnedShare ows : user.getPortfolio().getOwnedShares()) {
			if (ows.getShare().getShareId() == share.getShareId()) {
				return ows;
			}
		}
		
		return null;
	}
	
	@PostMapping("/buy")
	public ResponseEntity<?> shareBuy(@RequestBody @Valid ShareBuyDTO shareBuyInfo, BindingResult bindingResult) 
	{
		/*
		 * 
		 * 
		 * Request body
		 * {userId: , shareId: ,quantity: }
		 * 
		 * 
		 */
		
		if (bindingResult.hasErrors()) {
			return Response.badValue("Invalid Data", "Some data were missing").build();
		}
		
		Optional<User> user = userService.getUserById(shareBuyInfo.getUserId()); //Get the user who performs the buy operation
		Portfolio userPortfolio = user.get().getPortfolio(); //Get the portfolio of the user who performs the buy operation

		
		if (user.isEmpty()) { //USER NOT FOUND
			return Response.notFound(String.format("User with id: %d not found!", shareBuyInfo.getUserId())).build();
		}
		
		if (user.get().getPortfolio() == null) { //USER DOES NOT HAVE A REGISTERED PORTFOLIO
			return Response.badValue("User doesn't have a portfolio", "No portfolio").build();
		}
		
		Optional<Share> share = shareService.getShareById(shareBuyInfo.getShareId()); //Get the share which is going to be bought
		
		if(share.isEmpty()) {
			return Response.notFound(String.format("Share with id: %d not found!", shareBuyInfo.getShareId())).build();	//SHARE WITH THIS ID DOES NOT EXISTS
		}
		
		double totalValue = shareBuyInfo.getQuantity() * share.get().getShareRate().doubleValue(); //TO DECREASE THE USER BALANCE, CALCULATES THE RATE * QUANTITY BOUGHT
		
		// CHECKING USER BALANCE
		if (totalValue > user.get().getUserBalance()) {
			return Response.badValue("Not enough balance!", "NO BALANCE").build();
		}
		
		user.get().setUserBalance(user.get().getUserBalance() - totalValue); //ALL IS WELL AND ALL THE NECESSARY CONDITIONS ARE PROVIDED FOR BUY OPERATION
		OwnedShare ownedShare = getOwnedShareFromShare(user.get(), share.get());
		
		//If the user already owns some from the related share or not - CHECKING
		if (ownedShare.getQuantity() == 0) { //If not, create new entity for owned share
			ownedShare = new OwnedShare(share.get(),shareBuyInfo.getQuantity());
			ownedShare = shareService.updateOwnedShare(ownedShare);
			userPortfolio.getOwnedShares().add(ownedShare);
		} else { //If so, increase the quantity
			ownedShare.setQuantity(ownedShare.getQuantity() + shareBuyInfo.getQuantity());
			ownedShare = shareService.updateOwnedShare(ownedShare);
		}
		
		userService.updateUser(user.get()); //Update the user portfolio
		
		Transaction transaction = new Transaction("BUY", share.get(), user.get(), shareBuyInfo.getQuantity());
		transactionService.saveTransaction(transaction); //Save the transaction log on the DB
		
		return Response.ok("ok").body(TransactionDTO.fromTransaction(transaction)).build(); //Returns the transaction log entity for debug purposes
	}
	
	@PostMapping("/sell")
	public ResponseEntity<?> shareSell(@RequestBody @Valid ShareBuyDTO shareBuyInfo, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) { 
			return Response.badValue("Invalid Data", "Some data were missing").build();
		}
		
		Optional<User> user = userService.getUserById(shareBuyInfo.getUserId());
		Portfolio userPortfolio = user.get().getPortfolio();

		
		if (user.isEmpty()) {
			return Response.notFound(String.format("User with id: %d not found!", shareBuyInfo.getUserId())).build();
		}
		
		if (user.get().getPortfolio() == null) {
			return Response.badValue("User doesn't have a portfolio", "No portfolio found").build();
		}
		
		Optional<Share> share = shareService.getShareById(shareBuyInfo.getShareId());
		
		if(share.isEmpty()) {
			return Response.notFound(String.format("Share with id: %d not found!", shareBuyInfo.getShareId())).build();			
		}
		
		OwnedShare ownedShare = getOwnedShareFromShare(user.get(), share.get());
		
		if (ownedShare.getQuantity() < shareBuyInfo.getQuantity()) //USER OWNED SHARE QUANTITY < THE QUANTITY REQUESTED TO BE SOLD
		{
			return Response.badValue("User has insufficient amount of share", "Insufficient Share").build();

		} 
		else 
		{
			ownedShare.setQuantity(ownedShare.getQuantity() - shareBuyInfo.getQuantity()); //DECREASE THE QUANTITY OWNED FOR THE RELATED SHARE ON THE DB
			ownedShare = shareService.updateOwnedShare(ownedShare);
			double totalValue = shareBuyInfo.getQuantity() * share.get().getShareRate().doubleValue(); //ADD THE AMOUNT OF BALANCE TO THE USER ACCOUNT
			user.get().setUserBalance(user.get().getUserBalance() + totalValue);
		}
		
		userService.updateUser(user.get());
		
		Transaction transaction = new Transaction("SELL", share.get(), user.get(), shareBuyInfo.getQuantity());
		transactionService.saveTransaction(transaction);//Save the transaction log on the DB
		
		return Response.ok("ok").body(TransactionDTO.fromTransaction(transaction)).build();//Returns the transaction log entity for debug purposes
	}
	
}
