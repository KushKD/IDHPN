package com.dit.himachal.services;

import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.repositories.RolesRepository;
import com.dit.himachal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	private RolesRepository rolesRepository;


	public RolesRepository getRolesRepository() {
		return rolesRepository;
	}

	public void setRolesRepository(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	public RolesEntity  saveRole(RolesEntity entity) {
		return rolesRepository.save(entity);
		
	}

}
