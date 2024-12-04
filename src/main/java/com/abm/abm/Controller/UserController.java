package com.abm.abm.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.abm.abm.Entity.Survey;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
               if(createUsers == null) {
                    return ResponseUtil.errorResponse("User already exists", HttpStatus.CONFLICT.value());
               }
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

     @RequestMapping(value = "me", method = RequestMethod.GET)
     public ResponseEntity<Map<String, Object>> me(HttpServletRequest request) {
          MstUsers data = userService.me(request);

          if(data == null) {
               return ResponseUtil.errorResponse("No user found", HttpStatus.NOT_FOUND.value());
          }

          return ResponseUtil.successResponse(data);
     }

     @PostMapping(value = "update/{id}")
     public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") Integer id,@RequestBody MstUsers mstUsers) {
          try {
               Optional<MstUsers> updatedUser = userService.updateUser(id, mstUsers);

               if(updatedUser.isEmpty()) {
                    return ResponseUtil.errorResponse("User Not found", HttpStatus.NOT_FOUND.value());
               }
               MstUsers response = updatedUser.get();
               return ResponseUtil.successResponse(response);
          } catch (Exception e) {
               return ResponseUtil.errorResponse("Internal Server Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
          }
     }

     @PostMapping(value = "save-survey")
     public ResponseEntity<Map<String, Object>> saveSurvey(@RequestBody Survey survey) {
          try {
               Survey data = userService.saveSurvey(survey);

               return ResponseUtil.successResponse(data);
          } catch (Exception e) {
               return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
          }
     }
}
