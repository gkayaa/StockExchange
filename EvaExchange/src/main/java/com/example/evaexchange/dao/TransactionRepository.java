package com.example.evaexchange.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.evaexchange.model.Transaction;
import com.example.evaexchange.model.User;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> 
{
	/*
	 * 
	 * 
	 * CRUD REPOSITORY - Create - Read - Update - Delete 
	 * 
	 * 
	 */
	List<Transaction> findTransactionInfoByUser(User user);
}
