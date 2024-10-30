package com.abm.abm.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Repository.UserRepository;
import com.abm.abm.Utils.BadRequest;
import com.abm.abm.Utils.Error;
import com.abm.abm.Validator.UserValidation;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private UserValidation userValidation;

     private final PasswordEncoder passwordEncoder;
    
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
     
     public List<MstUsers> getUser() {

          List<MstUsers> mst_users = new ArrayList<>();

          userRepository.findAll()
               .forEach(users -> mst_users.add(users));
          if(mst_users.size() <= 0) {
               return null;
          }
          return mst_users;
     }


     public MstUsers createUsers(MstUsers mstUsers) {
          List<Error> errors = userValidation.createUpdateRequest(mstUsers);
          String encodePassword = passwordEncoder.encode(mstUsers.getPassword());
          mstUsers.setUser_type(0);
          mstUsers.setPassword(encodePassword);
            if(errors.size() > 0) {
               throw new BadRequest("Bad Request",errors);
          }
          return userRepository.save(mstUsers);
     }
}
