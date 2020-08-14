package com.dit.himachal.controller;

import com.dit.himachal.HTTP;
import com.dit.himachal.entities.VahanLog;
import com.dit.himachal.form.SaarthiForm;
import com.dit.himachal.form.VahanService;
import com.dit.himachal.modals.SaarthiObject;
import com.dit.himachal.modals.VahanObject;
import com.dit.himachal.modals.VehicleDetailsObject;
import com.dit.himachal.utilities.Constants;
import com.dit.himachal.utilities.ParseXML;
import com.dit.himachal.utilities.Utilities;
import com.dit.himachal.validators.SaarthiServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class SaarthiServiceController {

    @Autowired
    SaarthiServiceValidator saarthiServiceValidator;

    @RequestMapping(value = "/saarthiService", method = RequestMethod.GET)
    public String searchIdCard(Model model) {
        model.addAttribute("saarthiDetails", new SaarthiForm());
        return "sarthiDetail";
    }

    //saarthiService
    @RequestMapping(value = "/saarthiServiceGet", method = RequestMethod.POST)
    public String getSaarthiData(@ModelAttribute("saarthiDetails") SaarthiForm saarthiData, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        saarthiServiceValidator.validate(saarthiData, bindingResult);
        HTTP http = new HTTP();

        if (bindingResult.hasErrors()) {
            return "sarthiDetail";
        }

        //TODO
        System.out.println(saarthiData.toString());
        SaarthiObject result = http.getSarthiData(saarthiData.getDlNumber().trim());

        if (Utilities.positiveNegitive(result.getErrorCode())) {
            model.addAttribute("serverError", result.getErrorMessage());
            saarthiData.setDlNumber(saarthiData.getDlNumber());
            return "sarthiDetail";
        } else {
            //Save Data TODO

            request.getSession().setAttribute("successMessage", "Request was successful.");
            model.addAttribute("saarthi", result);
            saarthiData.setDlNumber(saarthiData.getDlNumber());
            return "sarthiDetail";
        }


    }





}
