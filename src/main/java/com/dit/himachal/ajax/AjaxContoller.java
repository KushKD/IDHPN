package com.dit.himachal.ajax;

import com.dit.himachal.ModalInterfaces.RoleIdName;
import com.dit.himachal.entities.BarrierMaster;
import com.dit.himachal.entities.DistrictMaster;
import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.modals.RolesModal;
import com.dit.himachal.repositories.RolesRepository;
import com.dit.himachal.services.BarrierService;
import com.dit.himachal.services.DistrictService;
import com.dit.himachal.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class AjaxContoller {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    DistrictService districtService;

    @Autowired
    BarrierService barrierService;


    @RequestMapping(value = "/ajax/getRoles", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getRoles() {
        Map<String, Object> map = null;
        List<Object[] > roles = rolesRepository.getRoles();
        List<RolesModal> modelRole = new ArrayList<>();


        for (Object[] result : roles) {
            RolesModal pojo = new RolesModal();
            pojo.setRole_id((Integer) result[0]);
            pojo.setRole_name((String) result[1]);
            System.out.println(
                    "ID: " + result[0] + ", Role: " + result[1]);
            modelRole.add(pojo);
        }

      //  System.out.println(roles.get(0).getId() + " fdfdfd" + roles.get(0).getName());
        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, modelRole);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

    @RequestMapping(value = "/ajax/getDistricts", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getDistricts() throws Exception {
        Map<String, Object> map = null;
        List<DistrictMaster> districts = districtService.getDistricts(9);

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, districts);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }

    //getBarriers
    @RequestMapping(value = "/ajax/getBarriers", method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody
    ResponseEntity<?> getBarriers(@RequestParam(value = "id", required = true) String id) throws Exception {
        Map<String, Object> map = null;
        List<BarrierMaster> barriers = barrierService.getBarriers(Integer.parseInt(id));

        map = new HashMap<String, Object>();
        map.put(Constants.keyResponse, barriers);
        map.put(Constants.keyMessage, Constants.valueMessage);
        map.put(Constants.keyStatus, HttpStatus.OK);
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);


    }
}
