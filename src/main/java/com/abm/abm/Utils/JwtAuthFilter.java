package com.abm.abm.Utils;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.abm.abm.Config.UserInfoUserDetailsService;
import com.abm.abm.Entity.MstUsers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter{

     public static final int JWT_TOKEN_START_INDEX = 7;
     
     @Autowired
     private JwtUtil jwtUtil;

     @Autowired
     private UserInfoUserDetailsService userInfoUserDetailsService;

     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        MstUsers username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(JWT_TOKEN_START_INDEX);
            username = jwtUtil.getUserByToken(token);
        }
        try {
          
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(username.getEmail());
            if (jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken;
                authToken = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        } catch (RuntimeException e) {
                         SecurityContextHolder.clearContext();
                         throw e;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Set the Jwt token for the authentication.
     * @param jwtUtil
    */
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * To get the User Details
     * @param userDetailsService
     */
    public void setUserDetailsService(UserInfoUserDetailsService userInfoUserDetailsService) {
        this.userInfoUserDetailsService = userInfoUserDetailsService;
    }
}
