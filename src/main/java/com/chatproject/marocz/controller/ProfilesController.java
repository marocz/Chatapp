package com.chatproject.marocz.controller;

import com.chatproject.marocz.dto.*;
import com.chatproject.marocz.entities.User;
import com.chatproject.marocz.model.MessageStatus;
import com.chatproject.marocz.repository.ProfileRepository;
import com.chatproject.marocz.repository.UserRepository;
import com.chatproject.marocz.services.ProfileServices;

import com.chatproject.marocz.model.Profile;
import com.chatproject.marocz.model.Profileadmin;
import com.chatproject.marocz.services.*;
import org.apache.http.client.methods.*;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import java.util.List;


@RestController
@RequestMapping(path = "/profiles")
public class ProfilesController {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ProfileServices profileServices;
    @Autowired
    ProfileadminServices profileadminServices;

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
    public List<Profile> getAllProfiles(){
        return profileServices.getProfiles();
    }
    @PostMapping("/email")
    @ResponseBody
    public Profile getAllProfiles(@Valid @RequestBody Model1DTO profileDTO){


        return profileServices.getProfile(profileDTO.getEmail());
    }
    @PostMapping("/loggedusers")
    public Profile getloginuser(Model model, @RequestBody String1DTO string ) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();

        User users = userRepository.findByUsername(string.getUsername());
        // String id = users.getEmplid();

        System.out.println(string.getUsername());
        System.out.println(users.getUsername());


        return profileServices.getProfile(users.getEmail());
    }
    @PostMapping("/loggedadmin")
    public Profileadmin getloginusers(Model model, @RequestBody String1DTO string ) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();

        User users = userRepository.findByUsername(string.getUsername());
        // String id = users.getEmplid();

        System.out.println(string.getUsername());
        System.out.println(users.getUsername());


        return profileadminServices.getProfile(users.getEmail());
    }

    @PostMapping("/add")
    @ResponseBody
    public Profile addProfile(@Valid @RequestBody ProfileDTO profileDTO){

        return profileServices.addProfile(profileDTO);
    }
    @PostMapping("/addnewaccount")
    @ResponseBody
    public String addProfilesd(@Valid @RequestBody CreateNewAccountDTO profileDTO){
        try {
            if (!userService.findByUsername(profileDTO.getUsername()).getUsername().isEmpty() ) {
                try{
                    if (!userService.findByUsername(profileDTO.getUsername()).getUsername().isEmpty()){

                        return "username already exist";
                    }

                } catch (NullPointerException f){
                    return "username already exist";

                }


            }else {
                createNewAccount.addNewProfile(profileDTO);
            }
        }
        catch (NullPointerException e){
            try {
                if (!userService.findByEmail(profileDTO.getEmail()).getEmail().isEmpty()) {
                    return "email already exist";
                }
            }
            catch (NullPointerException j) {
                createNewAccount.addNewProfile(profileDTO);
            }
            return "user created successfully";
        }

        return "user created successfully";
    }

    @PostMapping("/phonecode")
    @ResponseBody
    public String addPhone(@Valid @RequestBody ProfileDTO profileDTO){

        return profileServices.addPhone(profileDTO);
    }
    @PostMapping("/changepasskey")
    @ResponseBody
    public String changePassword(@Valid @RequestBody ChangePassword profileDTO){

        return profileServices.changePassword(profileDTO);
    }
    @PostMapping("/setphonecode")
    @ResponseBody
    public String setPhone(@Valid @RequestBody ProfileDTO profileDTO){

        return profileServices.changePhone(profileDTO);
    }
    @PostMapping("/editprofile")
    @ResponseBody
    public Profile editPhone(@Valid @RequestBody EditProfileDTO profileDTO){

        return profileServices.editProfile(profileDTO);
    }
    @PostMapping("/editprofileadmin")
    @ResponseBody
    public Profileadmin editPhoneadmin(@Valid @RequestBody EditProfileDTO profileDTO){

        return profileadminServices.editProfileadmin(profileDTO);
    }

    @PostMapping("/changeavatar")
    @ResponseBody
    public Profile editAvatars(@Valid @RequestBody EditProfileDTO2 profileDTO){

        return profileServices.editAvatar(profileDTO);
    }
    @PostMapping("/changeavataradmin")
    @ResponseBody
    public Profileadmin editAvatarsadmin(@Valid @RequestBody EditProfileDTO2 profileDTO){

        return profileadminServices.editAvataradmin(profileDTO);
    }

}

