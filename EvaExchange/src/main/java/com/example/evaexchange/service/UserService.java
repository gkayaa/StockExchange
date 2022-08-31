package com.example.evaexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaexchange.dao.PortfolioRepository;
import com.example.evaexchange.dao.UserRepository;
import com.example.evaexchange.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	/*
	 * 
	 * 
	 * Service class for users (unnecessary for the endpoints required)
	 * 
	 */
	
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        userRepo.findAll();
        return list;
    }

   
    public Optional<User> getUserById(Integer userId){
    	return userRepo.findById(userId);
    }
    
    public User updateUser(User user) {
    	portfolioRepository.save(user.getPortfolio());
    	return userRepo.save(user);
    }
    

}