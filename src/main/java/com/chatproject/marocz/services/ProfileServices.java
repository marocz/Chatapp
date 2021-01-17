package com.chatproject.marocz.services;

import com.chatproject.marocz.dto.ChangePassword;
import com.chatproject.marocz.dto.ProfileDTO;
import com.chatproject.marocz.entities.User;
import com.chatproject.marocz.model.ConfirmationToken;
import com.chatproject.marocz.model.Profile;
import com.chatproject.marocz.repository.ConfirmationTokenRepository;
import com.chatproject.marocz.repository.ProfileRepository;
import com.chatproject.marocz.repository.UserRepository;
import com.chatproject.marocz.repository.*;
import com.chatproject.marocz.services.*;
import com.chatproject.marocz.dto.*;
import com.chatproject.marocz.model.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServices {

    String username = "";
    String apiKey = "";

   // AfricasTalking.initialize(username, apiKey);

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

    public Profile getProfile(String email){
        try {
            return profileRepository.findByEmail(email);
        }catch (NullPointerException e){
            Profile profile = new Profile();
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

    public Profile editProfile(EditProfileDTO profile){
        Profile profiles = profileRepository.findByEmail(profile.getEmail());

        profiles.setPhone(profile.getPhone());

        return profileRepository.save(profiles);

    }
    public Profile editAvatar(EditProfileDTO2 profile){
        Profile profiles = profileRepository.findByEmail(profile.getEmail());

        profiles.setUseravatar(profile.getUserAvatar());

        return profileRepository.save(profiles);

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
    public String addPhone(ProfileDTO profile) {


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

        String theurl = "=Courrier&to=" + profiles.getPhone() + "&body=Your%20token%20is%20"+ tokens +"&dnd=2";
        System.out.println(tokens);

        String theoutput = "";


        theoutput = postXMLData( theurl);
        if(theoutput.contains("100")){
            System.out.println("100");
        }
        else{
            System.out.println(theoutput);
        }

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

            return numbers;


        } else {
            int[] numbers2 = new int[3];
            return numbers2;

        }


    }
    public String postXMLData( String urlpath){
        String result = "";
        try {
            URL myurl = new URL(urlpath);
            URLConnection urlconn = myurl.openConnection();

            urlconn.setRequestProperty("Content-Type", "text/xml");
            urlconn.setDoOutput(true);
            urlconn.setDoInput(true);
            urlconn.connect();

            //Create a writer to the url
            PrintWriter pw = new PrintWriter(urlconn.getOutputStream());
         //   pw.write(xmldata, 0, xmldata.length());
            pw.close();

            //Create a reader for the output of the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                result = result + line + "\n";

                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}