package com.abm.abm.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Utils.Error;

@Component
public class UserValidation {
          public List<Error> createUpdateRequest(MstUsers mstUsers) {
          List<Error> errors = new ArrayList<>();

          if(mstUsers.getFirst_name() == null){
               Error error = new Error("first_name", "First Name is Null");
               errors.add(error);
          }
          return errors;
     }
}
