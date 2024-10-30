package com.abm.abm.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.abm.abm.DTO.LoginRequestDto;
import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Repository.AuthRepository;
import com.abm.abm.Utils.JwtUtil;


@Service
public class AuthService {

     @Autowired
     private AuthRepository authRepository;

     @Autowired
     private JwtUtil jwtUtil;

     @Autowired
     private AuthenticationManager authenticationManager;

     public Map<String, Object> login(@RequestBody LoginRequestDto loginRequestDto) {
         MstUsers user = authRepository.findOneByEmail(loginRequestDto.getEmail());
         String email = loginRequestDto.getEmail();
         String password = loginRequestDto.getPassword();
          UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(email, password);
          Authentication authentication = authenticationManager.authenticate(authToken);
          String token = "";
          if(authentication.isAuthenticated()) {
               token = jwtUtil.generateToken(user);
               Map<String, Object> data = new HashMap<>();
               data.put("accessToken", token); 
               data.put("user", user);
               return data;
          } else {
               return null;
          }
     }
}
