package com.dit.himachal.ajax;

import com.dit.himachal.ModalInterfaces.RoleIdName;
import com.dit.himachal.entities.RolesEntity;
import com.dit.himachal.modals.RolesModal;
import com.dit.himachal.repositories.RolesRepository;
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
}
