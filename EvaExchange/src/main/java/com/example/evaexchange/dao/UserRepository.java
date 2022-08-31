package com.example.evaexchange.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.evaexchange.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	/*
	 * 
	 * 
	 * CRUD REPOSITORY - Create - Read - Update - Delete 
	 * 
	 * 
	 */
}