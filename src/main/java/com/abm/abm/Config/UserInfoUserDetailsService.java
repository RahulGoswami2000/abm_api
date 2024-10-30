package com.abm.abm.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Repository.AuthRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{
     @Autowired
     private AuthRepository authRepository;

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          MstUsers userInfo = authRepository.findOneByEmail(email);

          if(userInfo == null) {
               throw new UsernameNotFoundException("User not found" + email);
          }
          return new UserInfoUserDetails(userInfo);
     }
}
