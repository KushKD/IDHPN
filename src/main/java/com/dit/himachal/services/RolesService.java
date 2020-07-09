package com.dit.himachal.services;

import com.dit.himachal.ModalInterfaces.RoleIdName;
import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.modals.RolesModal;
import com.dit.himachal.repositories.RolesRepository;
import com.dit.himachal.repositories.UserRepository;
import org.apache.catalina.mbeans.RoleMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;


	public RolesRepository getRolesRepository() {
		return rolesRepository;
	}

	public void setRolesRepository(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	public List<Object[]> getRoles(){
		return rolesRepository.getRoles();
	}



}
