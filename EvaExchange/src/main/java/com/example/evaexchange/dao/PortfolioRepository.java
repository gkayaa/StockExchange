package com.example.evaexchange.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.evaexchange.model.Portfolio;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Long>
{
	/*
	 * 
	 * 
	 * CRUD REPOSITORY - Create - Read - Update - Delete 
	 * 
	 * 
	 */
	List<Portfolio> findPortfolioByUserId(int userId);
}
