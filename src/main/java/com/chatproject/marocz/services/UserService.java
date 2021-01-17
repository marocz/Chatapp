package com.chatproject.marocz.services;


import com.chatproject.marocz.entities.User;

public interface UserService {
    void save(User user);
    void adminsave(User user);
    User findByUsername(String username);
    void setProfilepics(String username, String picsurl);
    String getProfilepics(String username);
    boolean checkIfValidOldPassword(final User user, final String oldPassword);


}
