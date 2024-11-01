package com.abm.abm.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Services.UserService;
import com.abm.abm.Utils.BadRequest;
import com.abm.abm.Utils.ResponseUtil;

@RestController
public class UserController {
     @Autowired
     private UserService userService;

     @RequestMapping(value = "user-list")
     public ResponseEntity<Map<String, Object>> getMstUsers() {
          List<MstUsers> users = userService.getUser();
          if(users.isEmpty()){
               return ResponseUtil.errorResponse("No users found", HttpStatus.NOT_FOUND.value());
          }
          return ResponseUtil.successResponse(users);
     }

     /**
      * This function is for creating the users or doctors
      * @param mstUsers
      * @return
      */
     @RequestMapping(value="create-user", method=RequestMethod.POST)
     public ResponseEntity<Map<String, Object>> createUsers(@RequestBody MstUsers mstUsers) {
          try {
               MstUsers createUsers = userService.createUsers(mstUsers);
               return ResponseUtil.successResponse(createUsers);    
          } catch (BadRequest e) {
               return ResponseUtil.badRequest(e, HttpStatus.BAD_REQUEST.value());
          } catch (RuntimeException e) {
               return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
          }
     }

     @RequestMapping(value = "save-preferences/{id}", method = RequestMethod.POST)
     public ResponseEntity<Map<String, Object>> savePreferences(@PathVariable Integer id, @RequestParam Integer weed_id) {
          try {
               Optional<MstUsers> mst_users = userService.savePreference(id, weed_id);

               if(mst_users.isEmpty() ) {
                    return ResponseUtil.errorResponse("User not found", HttpStatus.NOT_FOUND.value());
               }
               MstUsers response = mst_users.get();
               return ResponseUtil.successResponse(response);
          } catch (Exception e) {
               return ResponseUtil.errorResponse("Internal Server Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
          }
     }
}
