package com.dit.himachal.controller;

import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.CustomLogin.SecurityService;
import com.dit.himachal.form.LoginForm;
import com.dit.himachal.modals.ReCaptchaResponse;
import com.dit.himachal.services.UserService;
import com.dit.himachal.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping(value = "/login")
//    public String homePage(Model model) {
//
//        System.out.println("We are here inside login");
//        return "login";
//    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

        @GetMapping(value =  "/verifylogin")
        public String homePagev(@RequestBody LoginForm data_) {
            System.out.println("#@#@#@#@#@#"+data_);
           // if(captchaResponse!=null ){

             //   String url = "https://www.google.com/recaptcha/api/siteverify";
               // String params = "?secret=6LeFwKwZAAAAAH65Ik0Cp-h4hY8T0Z7HEwCyntIt&response="+captchaResponse;
               // ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
               // if(reCaptchaResponse.isSuccess()) {

                 //   System.out.println("Valid");
                  //  return "index";
                //}else{
                  //  System.out.println("Captcha Invalid");
                   // return "login";
               // }
            //}else{
                return "login";
            //}


        }



    @RequestMapping(value =  "/logout" , method = RequestMethod.GET)
    public String logout(Model model) {
        System.out.println("Logout Successful");
      //  model.addAttribute("login", new Lo());
        return "login";
    }
}
