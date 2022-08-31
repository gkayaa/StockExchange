package com.example.evaexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaexchange.dao.TransactionRepository;
import com.example.evaexchange.model.Transaction;

@Service
public class TransactionService {

	/*
	 * 
	 * Service class for transactions
	 * 
	 * Saves the transaction logs on the DB
	 * 
	 */
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	public Transaction saveTransaction(Transaction tr) {
		return transactionRepository.save(tr);
	}
	
}
