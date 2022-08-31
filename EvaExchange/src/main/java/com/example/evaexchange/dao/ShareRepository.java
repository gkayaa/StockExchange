package com.example.evaexchange.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.evaexchange.model.Share;;

@Repository
public interface ShareRepository extends CrudRepository<Share, Integer> 
{
	/*
	 * 
	 * 
	 * CRUD REPOSITORY - Create - Read - Update - Delete 
	 * 
	 * 
	 */
	Optional<Share> findShareByShareName(String name);
}
