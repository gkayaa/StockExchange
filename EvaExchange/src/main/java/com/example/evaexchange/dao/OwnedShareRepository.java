package com.example.evaexchange.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.evaexchange.model.OwnedShare;

@Repository
public interface OwnedShareRepository extends CrudRepository<OwnedShare, Long>
{
	/*
	 * 
	 * 
	 * CRUD REPOSITORY - Create - Read - Update - Delete 
	 * 
	 * 
	 */
}
