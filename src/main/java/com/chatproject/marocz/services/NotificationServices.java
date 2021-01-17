package com.chatproject.marocz.services;

import com.chatproject.marocz.repository.*;
import com.chatproject.marocz.dto.NotificationDTO;
import com.chatproject.marocz.model.Notification;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationServices {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository chatRoomRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    private List<Notification> notifications = new ArrayList<>();

    public List<Notification> getNotification(){

        notifications.clear();
        chatRoomRepository.findAll().forEach(notify->{

            notifications.add(notify);
        });
        System.out.print(notifications);


        return notifications;

    }

    public List<Notification>  getNotifications(String email){
        try {
            return chatRoomRepository.findByEmail(email);
        }catch (NullPointerException e){
            List<Notification> profile = new ArrayList<>();
            System.out.println(profile);
            return  profile;
        }

    }

    public Notification addNotification(NotificationDTO profile, String email){
        Notification notification = new Notification();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());


        notification.setEmail(email);
        notification.setTitle(profile.getTitle());
        notification.setImage(profile.getImage());
        notification.setText(profile.getText());
        notification.setDate(date.toString());
        notification.setStatus("Active");

        return chatRoomRepository.save(notification);

    }

    public Notification deleteNotification(Long profile){

        Notification notification = chatRoomRepository.findById(profile).get();

        notification.setStatus("Deleted");

        return chatRoomRepository.save(notification);

    }
//    public String changePassword(ChangePassword profile){
//        Profile profiles = new Profile();
//        User user = userRepository.findByEmail(profile.getEmail());
//        System.out.println(profile.getEmail());
//        System.out.println(profile.getOldpassword());
//        System.out.println(user.getPassword());
//        String password = passwordEncoder.encode(profile.getOldpassword());
//        System.out.println(password);
//
//        if (!userService.checkIfValidOldPassword(user, profile.getOldpassword())) {
//            return "fail";
//        }
//        else{
//
//
//        user.setPassword(profile.getNewpassword());
//
//        userService.save(user);
//
//        return "success";
//        }
//
//
//    }
//    public String addPhone(ProfileDTO profile){
//
//
//        Profile profiles = profileRepository.findByEmail(profile.getEmail());
//        String tokens = "";
//        StringBuilder str
//                = new StringBuilder();
//
//        profiles.setPhone(profile.getPhone());
//
//        profileRepository.save(profiles);
//        int[]  token = sendtokentomailp(profiles.getEmail());
//        for(int i = 0; i < token.length; i++){
//
//            String string = String.valueOf(token[i]);
//            str.append(string);
//            System.out.println(string);
//            tokens = str.toString();
//            System.out.println(tokens);
//        }
//        System.out.println(tokens);
//        return tokens;
//
//    }
//    public String changePhone(ProfileDTO profile){
//
//
//        Profile profiles = profileRepository.findByEmail(profile.getEmail());
//
//        profiles.setPhone(profile.getPhone());
//
//        profileRepository.save(profiles);
//
//        return "success";
//
//    }

}