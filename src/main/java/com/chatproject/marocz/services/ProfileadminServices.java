package com.chatproject.marocz.services;

import com.chatproject.marocz.dto.ChangePassword;
import com.chatproject.marocz.dto.EditProfileDTO;
import com.chatproject.marocz.dto.EditProfileDTO2;
import com.chatproject.marocz.dto.ProfileDTO;
import com.chatproject.marocz.entities.User;
import com.chatproject.marocz.model.ConfirmationToken;
import com.chatproject.marocz.repository.ConfirmationTokenRepository;
import com.chatproject.marocz.repository.ProfileRepository;
import com.chatproject.marocz.repository.ProfileadminRepository;
import com.chatproject.marocz.repository.UserRepository;
import com.chatproject.marocz.model.Profile;
import com.chatproject.marocz.model.Profileadmin;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileadminServices {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ProfileadminRepository profileadminRepository;
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
    private PasswordEncoder passwordEncoder;



    private List<Profile> profiles = new ArrayList<>();

    public List<Profile> getProfiles(){

        profiles.clear();
        profileRepository.findAll().forEach(profile->{

            profiles.add(profile);
        });
        System.out.print(profiles);


        return profiles;

    }

    public Profileadmin getProfile(String email){
        try {
            return profileadminRepository.findByEmail(email);
        }catch (NullPointerException e){
            Profileadmin profile = new Profileadmin();
            System.out.println(profile);
            return  profile;
        }

    }

    public Profile addProfile(ProfileDTO profile){
        Profile profiles = new Profile();
        User user = new User();


        user.setEmail(profile.getEmail());
        user.setUsername(profile.getUsername());
        user.setStatus("Active");
        user.setPassword(profile.getPassword());

        userService.save(user);

        profiles.setUsername(profile.getUsername());
        profiles.setEmail(profile.getEmail());
        profiles.setGender(profile.getGender());
        profiles.setPhone(profile.getPhone());
        profiles.setUseravatar(profile.getUseravatar());
        profiles.setUserAvatarInternet(profile.getUserAvatarInternet());


        return profileRepository.save(profiles);

    }

    public Profileadmin editProfileadmin(EditProfileDTO profile){
        Profileadmin profiles = profileadminRepository.findByEmail(profile.getEmail());

        profiles.setPhone(profile.getPhone());

        return profileadminRepository.save(profiles);

    }
    public Profileadmin editAvataradmin(EditProfileDTO2 profile){
        Profileadmin profiles = profileadminRepository.findByEmail(profile.getEmail());

        profiles.setUseravatar(profile.getUserAvatar());

        return profileadminRepository.save(profiles);

    }

    public String changePassword(ChangePassword profile){
        Profile profiles = new Profile();
        User user = userRepository.findByEmail(profile.getEmail());
        System.out.println(profile.getEmail());
        System.out.println(profile.getOldpassword());
        System.out.println(user.getPassword());
        String password = passwordEncoder.encode(profile.getOldpassword());
        System.out.println(password);

        if (!userService.checkIfValidOldPassword(user, profile.getOldpassword())) {
            return "fail";
        }
        else{


        user.setPassword(profile.getNewpassword());

        userService.save(user);

        return "success";
        }


    }
    public String addPhone(ProfileDTO profile){


        Profile profiles = profileRepository.findByEmail(profile.getEmail());
        String tokens = "";
        StringBuilder str
                = new StringBuilder();

        profiles.setPhone(profile.getPhone());

        profileRepository.save(profiles);
        int[]  token = sendtokentomailp(profiles.getEmail());
        for(int i = 0; i < token.length; i++){

            String string = String.valueOf(token[i]);
            str.append(string);
            System.out.println(string);
            tokens = str.toString();
            System.out.println(tokens);
        }
        System.out.println(tokens);
        return tokens;

    }
    public String changePhone(ProfileDTO profile){


        Profile profiles = profileRepository.findByEmail(profile.getEmail());

        profiles.setPhone(profile.getPhone());

        profileRepository.save(profiles);

        return "success";

    }
    public int[] sendtokentomailp(String email) {
        User existingUser = userRepository.findByEmailIgnoreCase(email);
        if(existingUser != null) {
            int[] numbers = new int[4];

            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 9);
            }

            // create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            // save it
            confirmationTokenRepository.save(confirmationToken);

            // create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("nairobley@gmail.com");
            mailMessage.setText("Your token is : "
                    + numbers[0] + numbers[1] + numbers[2] + numbers[3] );

            emailSenderService.sendEmail(mailMessage);

            return numbers;


        } else {
            int[] numbers2 = new int[3];
            return numbers2;

        }


    }
}