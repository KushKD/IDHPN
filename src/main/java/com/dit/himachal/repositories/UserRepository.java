package com.dit.himachal.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dit.himachal.entities.UserEntity;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
  
	
	@Query(value="SELECT * FROM users WHERE mobile_number =:mobile AND active = true ", nativeQuery = true)
	 UserEntity getUserDetails(@Param("mobile") Long mobile );

	UserEntity findByUsername(String username);



	@Query(value="SELECT * FROM users WHERE mobile_number =:mobile AND active = true ", nativeQuery = true)
	UserEntity findByMobileNumber(@Param("mobile") Long mobile);

	@Query(value="SELECT user_id FROM users WHERE username =:username_ AND active = true ", nativeQuery = true)
	List<Object[]> getUserID(@Param("username_") String username_);
}