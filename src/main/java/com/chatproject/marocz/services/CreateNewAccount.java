package com.chatproject.marocz.services;

import com.chatproject.marocz.dto.*;
import com.chatproject.marocz.entities.*;
import com.chatproject.marocz.model.*;
import com.chatproject.marocz.repository.*;
import com.chatproject.marocz.repository.ConfirmationTokenRepository;
import com.chatproject.marocz.repository.UserRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CreateNewAccount {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    UserService userService;
    @Autowired
    private NotificationRepository notificationRepository;

    public Profile addNewProfile(CreateNewAccountDTO profile){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());

        Profile profiles = new Profile();
        User user = new User();

        user.setEmail(profile.getEmail());
        user.setUsername(profile.getUsername());
        user.setStatus("Active");
        user.setPassword(profile.getPassword());

        userService.save(user);

        profiles.setUsername(profile.getUsername());
        profiles.setEmail(profile.getEmail());

        Notification notification = new Notification();
        notification.setEmail(profile.getEmail());
        notification.setImage("assets/nonotify.png");
        notification.setStatus("Active");
        notification.setTitle("Welcome to our world");
        notification.setText("You can begin making pickup orders right now");
        notification.setDate(date.toString());

        notificationRepository.save(notification);


        return profileRepository.save(profiles);

    }

}
