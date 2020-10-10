package com.lit.lms.services.impl;


import java.util.HashSet;

import com.lit.lms.repository.RoleRepository;
import com.lit.lms.repository.UserRepository;
import com.lit.lms.entities.User;
import com.lit.lms.entities.Roles;
import com.lit.lms.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    
	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;


	}

	@Override
    public void save(User user) {

        int ids = 1;
        Long id = new Long(ids);

        Optional<Roles> roles =  roleRepository.findById(id);
        List<Roles> allroles = new ArrayList<>();
        allroles.add(roles.get());
		
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(roles.get());
        user.setRoles(new HashSet<>(allroles));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
	

}
