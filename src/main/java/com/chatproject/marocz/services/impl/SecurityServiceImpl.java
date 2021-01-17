package com.chatproject.marocz.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatproject.marocz.entities.User;
import com.chatproject.marocz.services.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService{
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        User user = (User)  userDetails;
        if(passwordEncoder.matches(password, user.getPassword())) {
        	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        	
        	 authenticationManager.authenticate(usernamePasswordAuthenticationToken);

             if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                 logger.debug(String.format("Auto login %s successfully!", username));
             }
        }
        
    }
}
