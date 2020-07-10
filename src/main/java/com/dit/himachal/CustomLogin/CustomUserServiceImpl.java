package com.dit.himachal.CustomLogin;

import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.repositories.RolesRepository;
import com.dit.himachal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       // Iterable<RolesEntity> entity  = roleRepository.findAll();
       // user.setRoles(getListFromIterator(entity));
       // user.setRoles(new HashSet<>((Collection) roleRepository.findAll()));

        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserEntity findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(Long.valueOf(mobileNumber));
    }

}
