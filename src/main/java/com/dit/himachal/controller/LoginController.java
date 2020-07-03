package com.dit.himachal.controller;

import com.dit.himachal.form.LoginForm;
import com.dit.himachal.modals.ReCaptchaResponse;
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
    @GetMapping(value = "/login")
    public String homePage(Model model) {

        System.out.println("We are here inside login");
        return "login";
    }

//        @GetMapping(value =  "/verifylogin")
//        public String homePagev(@RequestBody LoginForm data_) {
//            System.out.println("#@#@#@#@#@#"+data_);
//           // if(captchaResponse!=null ){
//
//             //   String url = "https://www.google.com/recaptcha/api/siteverify";
//               // String params = "?secret=6LeFwKwZAAAAAH65Ik0Cp-h4hY8T0Z7HEwCyntIt&response="+captchaResponse;
//               // ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
//               // if(reCaptchaResponse.isSuccess()) {
//
//                 //   System.out.println("Valid");
//                  //  return "index";
//                //}else{
//                  //  System.out.println("Captcha Invalid");
//                   // return "login";
//               // }
//            //}else{
//                return "login";
//            //}
//
//
//        }


//    @RequestMapping(value = "/getDispatch", method = RequestMethod.POST)
//    @ResponseBody
//    public DispatchResponse getDispatch(@RequestBody DispatchForm dispatchForm, HttpServletRequest request, Model model) {
//        logger.info("Inside DispatchController : getDispatch ");
//
//        DispatchResponse res = null;
//        String responseJson = null;
//        try {
//            if (dispatchForm != null) {
//                String requestJson = dispatchService.getRequestJson(dispatchForm, request);
//                if (requestJson != null) {
//                    responseJson = dispatchService.getDispatch(dispatchForm, request, requestJson);
//                    if (responseJson != null) {
//                        res = dispatchService.getFormatedString(responseJson);
//                        if (res.getMsgStatus().getStatus().contains("PASS")) {
//                            history.logDispatch(res, Integer.parseInt(dispatchForm.getQuoteId()));
//                        }
//                    }
//                }
//            } else {
//                logger.error("DispatchController : getDispatch() - RequestBody is null or invalid");
//                throw new Exception("RequestBoday i.e. DispatchForm is null");
//            }
//            model.addAttribute("response", res);
//
//        } catch (Exception e) {
//            logger.error("Error getting dispatch: geDispatch() " + e.getCause());
//        }
//        return res;
//    }



//    @RequestMapping(value="/validateCaptcha", method = RequestMethod.POST)
//    public boolean validateCaptcha(HttpServletRequest captchaResponse){
//        RestTemplate restTemplate = new RestTemplate();
//        System.out.println("trtrtrtrtrtr"+captchaResponse.toString());
//        if(captchaResponse!=null ) {
//
//            String url = "https://www.google.com/recaptcha/api/siteverify";
//            String params = "?secret=6LeFwKwZAAAAAH65Ik0Cp-h4hY8T0Z7HEwCyntIt&response=" + captchaResponse;
//            ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url + params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
//            if (reCaptchaResponse.isSuccess()) {
//
//                System.out.println("Valid");
//                return true;
//            } else {
//                System.out.println("Captcha Invalid");
//                return false;
//            }
//        }else{
//            return false;
//        }
//    }



    @RequestMapping(value =  "/logout" , method = RequestMethod.GET)
    public String logout(Model model) {
        System.out.println("Logout Successful");
        return "login";
    }
}
