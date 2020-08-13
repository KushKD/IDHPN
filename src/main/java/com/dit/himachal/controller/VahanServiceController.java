package com.dit.himachal.controller;

import com.dit.himachal.HTTP;
import com.dit.himachal.entities.VahanLog;
import com.dit.himachal.entities.VehicleOwnerEntries;
import com.dit.himachal.form.SearchID;
import com.dit.himachal.form.VahanService;
import com.dit.himachal.modals.RolesModal;
import com.dit.himachal.modals.VahanObject;
import com.dit.himachal.modals.VehicleDetailsObject;
import com.dit.himachal.repositories.UserId;
import com.dit.himachal.repositories.VahanLogsRepository;
import com.dit.himachal.security.CryptographyAES;
import com.dit.himachal.services.UserService;
import com.dit.himachal.utilities.Constants;
import com.dit.himachal.utilities.ParseXML;
import com.dit.himachal.utilities.Utilities;
import com.dit.himachal.validators.VahanServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.List;

@Controller
public class VahanServiceController {

    @Autowired
    VahanServiceValidator vahanServiceValidator;
    
    @Autowired
    UserService userService;
    
    @Autowired
    VahanLogsRepository vahanLogsRepository;

    CryptographyAES AES = new CryptographyAES();

    @RequestMapping(value = "/vahanService", method = RequestMethod.GET)
    public String searchIdCard(Model model) {
        model.addAttribute("vahanDetails", new VahanService());
        return "vahanDetails";
    }

    @RequestMapping(value = "/getDetailsVehicle", method = RequestMethod.POST)
    public String getIdCardListSearch(@ModelAttribute("vahanDetails") VahanService vahanData, BindingResult bindingResult, Model model, HttpServletRequest request) {
        vahanServiceValidator.validate(vahanData, bindingResult);
        HTTP http = new HTTP();
        VahanObject data = null;

        if (bindingResult.hasErrors()) {
            return "vahanDetails";
        }
        try {
            System.out.println(vahanData.toString());


            VahanObject object = new VahanObject();
            if (vahanData.getServiceType().equalsIgnoreCase("vehicleDetails")) {
                object.setFunction_name(Constants.getCarDetailsVahan);
            } else if (vahanData.getServiceType().equalsIgnoreCase("chassisDetails")) {
                object.setFunction_name(Constants.getChessisDetailsVahan);
            } else {
                object.setFunction_name(Constants.getEngineDetailsVahan);
            }

            object.setUrl(Constants.vahan);
            object.setParameters_to_send(vahanData.getParameter());

             data = http.postData(object);

            if (data.getSuccessFail().equalsIgnoreCase("FALIURE")) {
                //Save Data TODO
                VehicleDetailsObject objectVehicle = null;
                VahanLog logs = createLog(data,Utilities.getClientIp(request),objectVehicle);
                vahanLogsRepository.save(logs);
                model.addAttribute("serverError", data.getResponse());
                vahanData.setParameter(data.getParameters_to_send());
                return "vahanDetails";
            } else {
                //Save Data TODO

                VehicleDetailsObject objectVehicle = ParseXML.parseXml(data.getResponse());
                VahanLog logs = createLog(data,Utilities.getClientIp(request),objectVehicle);
                vahanLogsRepository.save(logs);
                request.getSession().setAttribute("successMessage", "Request was successful.");
                model.addAttribute("vehicledata", objectVehicle);
                vahanData.setParameter(data.getParameters_to_send());
                return "vahanDetails";
            }


        } catch (Exception ex) {
            //Save Data
            VehicleDetailsObject objectVehicle = null;
            VahanLog logs = createLog(data,Utilities.getClientIp(request),objectVehicle);
            vahanLogsRepository.save(logs);
            vahanData.setParameter("");
            model.addAttribute("serverError", ex.toString());
            return "vahanDetails";
        }


    }

    private VahanLog createLog(VahanObject data, String clientIp, VehicleDetailsObject vObject) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        List<Object[] > userId = userService.getUserId(username);
        BigInteger userIdSer = null;
        for (Object[] result : userId) {
            userIdSer = (BigInteger) result[0];

        }


        VahanLog Log = new VahanLog();
        Log.setLogApplicationName("HP Transport ID");
        Log.setLogFunctionName(data.getFunction_name());
        Log.setLogIpAddress(clientIp);
        if(data.getSuccessFail().equalsIgnoreCase("FALIURE")){
            Log.setLogServiceResponseCode(404);
        }else{
            Log.setLogServiceResponseCode(200);
        }
        if(vObject!=null){
                Log.setEngineNumber(vObject.getRcEngineNumber());
                Log.setChassisNumber(vObject.getRcChassisNo());
                Log.setRcStatus(vObject.getRcStatus());
                Log.setRcRegisteredAt(vObject.getRcRegisteredAt());
                Log.setRegNo(vObject.getRcRegistrationNo());
                Log.setRcFitUpto(vObject.getRcFitUpto());
                Log.setRcStatusAsOnDate(vObject.getRcStatusAsOn());
                Log.setRcOwnerName(vObject.getRcOwnerName());
        }else{
            Log.setEngineNumber("");
            Log.setChassisNumber("");
            Log.setRcStatus("");
            Log.setRcRegisteredAt("");
            Log.setRegNo("");
            Log.setRcFitUpto("");
            Log.setRcStatusAsOnDate("");
            Log.setRcOwnerName("");
        }
        
        
        Log.setLogUserId(userIdSer.longValue());
        Log.setActive(true);
        Log.setLogVehicleNumber(data.getParameters_to_send());



        return Log;
    }

}
