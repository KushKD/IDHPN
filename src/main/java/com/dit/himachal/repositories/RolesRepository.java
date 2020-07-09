package com.dit.himachal.repositories;


import com.dit.himachal.ModalInterfaces.RoleIdName;
import com.dit.himachal.modals.RolesModal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.entities.UserEntity;

import java.util.List;
import java.util.Set;


@Repository
public interface RolesRepository extends CrudRepository<RolesEntity,Integer> {

    @Query(value = "SELECT role_id, role_name from roles where active = true", nativeQuery = true)
    public List<Object[]> getRoles();
//Object[]




}