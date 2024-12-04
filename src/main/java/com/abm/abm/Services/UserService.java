package com.abm.abm.Services;

import java.util.*;

import com.abm.abm.Entity.Survey;
import com.abm.abm.Repository.SurveyRepository;
import com.abm.abm.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Repository.UserRepository;
import com.abm.abm.Utils.BadRequest;
import com.abm.abm.Utils.Error;
import com.abm.abm.Validator.UserValidation;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private UserValidation userValidation;

     @Autowired
     private JwtUtil jwtUtil;

     @Autowired
     private SurveyRepository surveyRepository;

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
          boolean exists = userRepository.existsByEmail(mstUsers.getEmail());
         if (exists) {
             return null;
         }
          String encodePassword = passwordEncoder.encode(mstUsers.getPassword());
          mstUsers.setUser_type(0);
          mstUsers.setPassword(encodePassword);
            if(errors.size() > 0) {
               throw new BadRequest("Bad Request",errors);
          }
          mstUsers = userRepository.save(mstUsers);
          return mstUsers;
     }


     public Optional<MstUsers> savePreference(Integer id, Integer weed_id) {
          Optional<MstUsers> isUserPresent = userRepository.findById(id);

          if(isUserPresent == null) {
               return null;
          }

          isUserPresent.ifPresent(user -> {
               user.setWeed_id(weed_id);
               userRepository.save(user);
          });
          return isUserPresent;
     }

    public MstUsers me(HttpServletRequest request){
        MstUsers user = jwtUtil.getUser(request);
        Integer id = user.getUser_id();
        List<Map<String, Object>> userPetDetails = userRepository.me(id);
        return user;
    }

    public Optional<MstUsers> updateUser(Integer id,MstUsers mstUsers) {
        Optional<MstUsers> isUserPresent = userRepository.findById(id);
        if(isUserPresent.isEmpty()) {
            return null;
        }
        System.out.println(mstUsers.getPassword());
        isUserPresent.ifPresent(user -> {
            user.setFirst_name(mstUsers.getFirst_name());
            user.setLast_name(mstUsers.getLast_name());
            user.setEmail(mstUsers.getEmail());
            userRepository.save(user);
        });
        return isUserPresent;
    }

    public Survey saveSurvey(@RequestBody Survey survey) {
        survey.setUser_id(survey.getUser_id());
        survey.setQuestion_1(survey.getQuestion_1());
        survey.setQuestion_2(survey.getQuestion_2());
        survey.setQuestion_3(survey.getQuestion_3());
        survey.setQuestion_4(survey.getQuestion_4());
        survey.setQuestion_5(survey.getQuestion_5());
        survey.setQuestion_6(survey.getQuestion_6());
        survey.setQuestion_7(survey.getQuestion_7());
        survey.setQuestion_8(survey.getQuestion_8());

        survey = surveyRepository.save(survey);

        return survey;
    }
}
