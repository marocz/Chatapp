package com.lit.lms.services;


import com.lit.lms.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
   
}
