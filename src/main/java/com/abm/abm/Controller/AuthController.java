package com.abm.abm.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abm.abm.DTO.LoginRequestDto;
import com.abm.abm.Services.AuthService;
import com.abm.abm.Utils.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin
@Controller
public class AuthController {
     @Autowired
     private AuthService authService;

     @PostMapping("/login")
     public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto loginRequestDto) {
          try {
               Map<String, Object> auth = authService.login(loginRequestDto);

               if(auth == null) {
                    return ResponseUtil.errorResponse("Login failed", HttpStatus.UNAUTHORIZED.value());
               }    
               return ResponseUtil.successResponse(auth);
          } catch (Exception e) {
               return ResponseUtil.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
          }
     }
}
