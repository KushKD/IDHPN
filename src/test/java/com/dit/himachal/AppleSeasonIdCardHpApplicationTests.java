package com.dit.himachal;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.repositories.RolesRepository;
import com.dit.himachal.repositories.UserRepository;
import com.dit.himachal.utilities.random24;

@SpringBootTest
class AppleSeasonIdCardHpApplicationTests {

	@Autowired
    RolesRepository rolesRepository;
//    @Autowired
//    UserRepository userRepository;
//
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void createUser(){
//
//        UserEntity user1 = new UserEntity();
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        user1.setUserName("kush");
//        user1.setPassword(encoder.encode("Demo@123"));
//        user1.setMobileNumber(9418008559L);
//        user1.setActive(true);
//
//        Optional<RolesEntity> role = rolesRepository.findById(1);
//		List<RolesEntity> list = new ArrayList<RolesEntity>();
//		list.add(role.get());
//        user1.setRoles(list);

//        UserEntity user2 = new UserEntity();
//        user2.setUserName("Demo2");
//        user2.setPassword("Demo@123");

       // userRepository.save(user1);
       // userRepository.save(user2);
    //}
////
////
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void createRoles() {
//
//        RolesEntity roles = new RolesEntity();
//        roles.setRoleName("Barrier");
//        roles.setRoleDescription("Person at Barrier");
//        roles.setActive(true);
//
//
//
//        rolesRepository.save(roles);
//
//    }
////
//    @Test
//    void bCryptPassword(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        encoder.encode("fagu@123");
//        System.out.printf("\n");
//        System.out.printf(encoder.encode("fagu@123"));
//        System.out.printf("\n");
//    }
//    
	
//  @Test
//  void checkRandom(){
//     
//      System.out.println("!@!@!@!@!"+random24.randomDecimalString(6)); 
//  }


//    @Test
//    void testDate() throws ParseException {
//        DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = originalFormat.parse("14-07-2020");
//        String formattedDate = targetFormat.format(date);
//        System.out.println(formattedDate);
//    }

//    @Test
//    private void date(){
//        System.out.println("-----Current time of a different time zone using LocalTime-----");
//        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
//        LocalTime localTime=LocalTime.now(zoneId);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String formattedTime=localTime.format(formatter);
//        System.out.println("Current time of the day in Los Angeles: " + formattedTime);
//    }

//    @Test
//    public  void convertStringSqlDate() {
//
//
//
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date parsed = null;
//
//        try {
//            parsed = format.parse("14-07-2020");
//        } catch (ParseException e) {
//
//        }
//
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//        System.out.println(sql);
//       // return sql;
//    }


	
	
    
}
