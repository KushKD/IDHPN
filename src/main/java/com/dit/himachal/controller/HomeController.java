package com.dit.himachal.controller;

import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.CustomLogin.SecurityService;
import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.form.RegisterUser;
import com.dit.himachal.form.RolesForm;
import com.dit.himachal.services.RoleService;
import com.dit.himachal.validators.RoleValidator;
import com.dit.himachal.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private CustomUserService userService;

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
    public String saveUser(@ModelAttribute("registerUser") RegisterUser registerUser, BindingResult bindingResult) {
        userValidator.validate(registerUser, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createuser";
        }

        // userService.save(userForm);

        // securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "createuser";
    }



    //RolesForm
    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
    public String createRole(Model model) {
        model.addAttribute("rolesForm", new RolesForm());
        return "createrole";
    }


    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("rolesForm") RolesForm roleForm, BindingResult bindingResult) {
        roleValidator.validate(roleForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "createrole";
        }
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setActive(true);
        rolesEntity.setRoleName(roleForm.getRoleName());
        rolesEntity.setRoleDescription(roleForm.getRoleDescription());
        RolesEntity savedData = roleService.saveRole(rolesEntity);

        ModelAndView mav = new ModelAndView();
        Map<String, String> message = new HashMap<String, String>();
        mav.addObject("message", savedData.toString());

        // securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "createrole";
    }


}
