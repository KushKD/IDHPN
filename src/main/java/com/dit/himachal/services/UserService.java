package com.dit.himachal.services;

import java.util.List;

import com.dit.himachal.repositories.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dit.himachal.entities.BarrierMaster;
import com.dit.himachal.entities.StatesMaster;
import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.repositories.BarrierRepository;
import com.dit.himachal.repositories.StateRepository;
import com.dit.himachal.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;


	

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	
	public UserEntity getUserDetails(Long mobileNumber) {
		return userRepository.getUserDetails(mobileNumber);
		
	}

	public UserEntity saveUser(UserEntity entity) {
		return userRepository.save(entity);

	}

	public List<Object[] > getUserId(String username){
		return userRepository.getUserID(username);
	}

}
