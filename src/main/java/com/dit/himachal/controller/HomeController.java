package com.dit.himachal.controller;

import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.CustomLogin.SecurityService;
import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.form.RegisterUser;
import com.dit.himachal.form.RolesForm;
import com.dit.himachal.form.showIdCardList;
import com.dit.himachal.services.RoleService;
import com.dit.himachal.services.UserService;
import com.dit.himachal.validators.RoleValidator;
import com.dit.himachal.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private UserService userservice;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String homePage(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority().toString());
        }

        System.out.println(username);
        return "homepage_new";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("We are here");

        return "index";
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("registerUser", new RegisterUser());
        return "createuser";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @Transactional
    public String saveUser(@ModelAttribute("registerUser") RegisterUser registerUser, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(registerUser, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createuser";
        }
        try{
            UserEntity user = new UserEntity();
            PasswordEncoder encoder  = new BCryptPasswordEncoder();
            user.setActive(true);
            user.setMobileNumber(Long.valueOf(registerUser.getMobileNumber()));
            user.setUserName(registerUser.getUsername());
            user.setPassword(encoder.encode(registerUser.getPassword()));
            String roleIid = registerUser.getRoleId();

            Optional<RolesEntity> role = roleService.getRoleDetails(roleIid);
            if(role.get()!=null){
                List<RolesEntity> list = new ArrayList<RolesEntity>();
                list.add(role.get());
                user.setRoles(list);
                UserEntity savedData = userservice.saveUser(user);

                 request.getSession().setAttribute("successMessage",  savedData.getUserName()+"  Successfully Saved. ID is" + savedData.getUserId() );
                registerUser.setMobileNumber("");
                registerUser.setPasswordConfirm("");
                registerUser.setPassword("");
                registerUser.setUsername("");
                registerUser.setRoleId("0");
                return "createuser";
            }else{
                registerUser.setMobileNumber("");
                registerUser.setPasswordConfirm("");
                registerUser.setPassword("");
                registerUser.setUsername("");
                registerUser.setRoleId("0");
                model.addAttribute("serverError", "No Role Name and Role Description Exist with this ID");
                return "createuser";
            }

        }catch(Exception ex){
            registerUser.setMobileNumber("");
            registerUser.setPasswordConfirm("");
            registerUser.setUsername("");
            registerUser.setPassword("");
            model.addAttribute("serverError", ex.toString());
            return "createuser";
        }

    }



    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
    public String createRole(Model model) {
        model.addAttribute("rolesForm", new RolesForm());
        return "createrole";
    }


    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("rolesForm") RolesForm roleForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        roleValidator.validate(roleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createrole";
        }
        try{
            RolesEntity rolesEntity = new RolesEntity();
            rolesEntity.setActive(true);
            rolesEntity.setRoleName(roleForm.getRoleName());
            rolesEntity.setRoleDescription(roleForm.getRoleDescription());
            RolesEntity savedData = roleService.saveRole(rolesEntity);
            roleForm.setRoleName("");
            roleForm.setRoleDescription("");
            request.getSession().setAttribute("successMessage",  savedData.getRoleName()+" role Successfully Saved. ID is" + savedData.getRoleId() );
            return  "createrole";
        }catch(Exception ex){
            roleForm.setRoleName("");
            roleForm.setRoleDescription("");
            model.addAttribute("serverError", ex.toString());
            return  "createrole";
        }
    }

    @RequestMapping(value = "/showIdCards", method = RequestMethod.GET)
    public String showIdCardList(Model model) {
        model.addAttribute("showIdCardList", new showIdCardList());
        return "showidcards";
    }


    @RequestMapping(value = "/getIdCards", method = RequestMethod.POST)
    public String getIdCardList(@ModelAttribute("rolesForm") RolesForm roleForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        roleValidator.validate(roleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createrole";
        }
        try {
            RolesEntity rolesEntity = new RolesEntity();
            rolesEntity.setActive(true);
            rolesEntity.setRoleName(roleForm.getRoleName());
            rolesEntity.setRoleDescription(roleForm.getRoleDescription());
            RolesEntity savedData = roleService.saveRole(rolesEntity);
            roleForm.setRoleName("");
            roleForm.setRoleDescription("");
            request.getSession().setAttribute("successMessage", savedData.getRoleName() + " role Successfully Saved. ID is" + savedData.getRoleId());
            return "createrole";
        } catch (Exception ex) {
            roleForm.setRoleName("");
            roleForm.setRoleDescription("");
            model.addAttribute("serverError", ex.toString());
            return "createrole";
        }
    }



}
