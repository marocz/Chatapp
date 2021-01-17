package com.chatproject.marocz.controller;

import com.chatproject.marocz.dto.NotificationDTO;
import com.chatproject.marocz.repository.ChatRoomRepository;
import com.chatproject.marocz.repository.UserRepository;
import com.chatproject.marocz.dto.*;
import com.chatproject.marocz.entities.User;
import com.chatproject.marocz.model.Notification;
import com.chatproject.marocz.services.CreateNewAccount;
import com.chatproject.marocz.services.NotificationServices;
import com.chatproject.marocz.services.ChatRoomService;
import org.apache.http.client.methods.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/notification")
public class NotificationController {

    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    NotificationServices notificationServices;
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    CreateNewAccount createNewAccount;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepository userService;

    @GetMapping("")
    @ResponseBody
    public List<Notification> getAllNotification(){
        return notificationServices.getNotification();
    }
    @PostMapping("/email")
    @ResponseBody
    public List<Notification> getAllProfiles(@Valid @RequestBody Model1DTO profileDTO){

        return notificationServices.getNotifications(profileDTO.getEmail());
    }

//
    @PostMapping("/addone")
    @ResponseBody
    public Notification addProfile(@Valid @RequestBody NotificationDTO notificationDTO){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();

        User users = userRepository.findByUsername(logname);
        return notificationServices.addNotification(notificationDTO, users.getEmail());
    }


    @PostMapping("/delete")
    @ResponseBody
    public Notification deleteNotification(@Valid @RequestBody String2DTO idDTO){
        long Id = Long.parseLong(idDTO.getId());
        return notificationServices.deleteNotification(Id);
    }
//    @PostMapping("/phonecode")
//    @ResponseBody
//    public String addPhone(@Valid @RequestBody ProfileDTO profileDTO){
//
//        return profileServices.addPhone(profileDTO);
//    }

}
