package com.example.evaexchange.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaexchange.dao.OwnedShareRepository;
import com.example.evaexchange.dao.ShareRepository;
import com.example.evaexchange.dao.UserRepository;
import com.example.evaexchange.model.OwnedShare;
import com.example.evaexchange.model.Share;

@Service
public class ShareService
{
	/*
	 * 
	 * 
	 * Service class for shares
	 * 
	 */
	
	 @Autowired
	 private ShareRepository shareRepo;
	 @Autowired
	 private UserRepository userRepo;
	 @Autowired
	 private OwnedShareRepository ownedShareRepository;
	 
	 public List<Share> getAllShares(){
		 List<Share> list = new ArrayList<>();
		 shareRepo.findAll().forEach(list::add);
		 return list;
	 }

	 public Optional<Share> getShareByName(String name){ //Optional : Represents null with 'absent' value
		 return shareRepo.findShareByShareName(name);
	 }
	 
	 public Optional<Share> getShareById(int shareId){
		 return shareRepo.findById(shareId);
	 }
	 
	 public OwnedShare updateOwnedShare(OwnedShare ow) {
		 return ownedShareRepository.save(ow);
	 }
	 
	 public void processBuyShare(int userId, int shareId) {
		 
	 }
	 
}
