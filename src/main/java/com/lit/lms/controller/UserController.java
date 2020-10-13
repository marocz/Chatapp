package com.lit.lms.controller;

import com.lit.lms.entities.User;
import com.lit.lms.repository.UserRepository;
import com.lit.lms.services.SecurityService;
import com.lit.lms.services.UserValidator;
import java.util.HashMap;
import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lit.lms.model.ConfirmationToken;
import com.lit.lms.entities.User;
import com.lit.lms.services.EmailSenderService;
import com.lit.lms.repository.ConfirmationTokenRepository;
import com.lit.lms.repository.UserRepository;


@RestController
public class UserController {

    @Autowired
    private UserRepository userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /*
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
		this.userService = userService;
		this.securityService = securityService;
		this.userValidator = userValidator;
	}
    */

    @GetMapping("/404")
    public String four(Model model) {

        return "404";
    }
    @GetMapping(value = {"/login","loginpage"})
    public String login(Model model, String error, String logout) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        String user = auth.getPrincipal().toString();
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");


        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");




        return "login";

    }
    @GetMapping(value = {"/index","/", "landingpage"})
    public String welcome(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();

        return "landingpage";
    }
    //    @GetMapping("/loginapi")
//    public String login(@ModelAttribute("UserForm") User userForm,
//                        Model model,
//                   //     @RequestParam(value = "username", defaultValue = "") String usernames,
//                  //      @RequestParam(value = "password") String passwords,
//                        final  HttpServletRequest request) throws URISyntaxException {
//
//        System.out.println("Starting API");
//        log.info("Starting API");
////         boolean result = false;
////
////        try
////        {
////            var pc = new PrincipalContext(ContextType.Domain, "10.100.5.231", "UBAGROUP\\sps_wss", "BizTalk3000");
////            result = pc.ValidateCredentials(model.Username, model.Password);
////
////        }
////        catch (Exception ex)
////        {
////            logger.Debug(ex);
////        }
//
//
//
//        final String username = userForm.getUsername();
//         final String password = userForm.getPassword();
//         log.info(password);
//
//
//         final String api = "http://10.100.5.195:8017/api/ADUser/AuthenticateUser?username="+username+"&&password="+password;
//         URI uri = new URI(api);
//         RestTemplate restTemplate = new RestTemplate();
//        // String result = restTemplate.getForObject(api, String.class);
//         final HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//
//
//        Map<String, String> map = new HashMap<>();
//       //  JSONObject record = new JSONObject();
//         map.put(username, username);
//         map.put(password, password);
//         System.out.println("API set");
//         log.info("API Set");
//
//         HttpEntity entity = new HttpEntity(headers);
//         System.out.println(entity);
//         log.info("API Set");
//
//        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
//                 //
//
//         System.out.println(response.getBody());
//
//         log.info(response.getBody());
//        log.info("Api logiin");
//        User user = userService.findByUsernameIgnoreCase(username);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//         if( response.getBody().equals("true") && user != null){
//
//
//             log.info(user.getUsername());
//             log.info(user.getPassword());
//
//       //      try {
//                 String pass = encoder.encode(user.getPassword());
//                 System.out.println(encoder.encode(user.getPassword()));
//                 authWithoutPassword(user);
//                 log.info("got heren 3");
////                 request.login(user.getUsername(), user.getPassword());
//     //        } catch (ServletException e) {
//       //          log.error("Error while login ", e);
//      //      }
//             return "redirect:/landingpage";
//         }
//
//         else{
//             log.info("Empty User");
////     	if (error != null)
////              model.addAttribute("error", "Your username and password is invalid.");
////
////
////          if (logout != null)
////              model.addAttribute("message", "You have been logged out successfully.");
//
//         }
//
//        return "redirect:/loginpage";
//
//    }
//    @GetMapping("/adminlogin")
//    public String login(Model model, String error, String logout) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        System.out.println(auth.getPrincipal());
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "adminlogin";
//
//    }
    @RequestMapping(value="/forgot-password", method=RequestMethod.POST)
    public String forgotUserPassword(ModelAndView modelAndView, @RequestBody User user) {
        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null) {
            // create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            // save it
            confirmationTokenRepository.save(confirmationToken);

            // create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("nairobley@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    +"http://localhost:8082/confirm-reset?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            return "Request to reset password received. Check your inbox for the reset link.";


        } else {
            return "This email does not exist!";

        }


    }

    public void authWithoutPassword(User user) {


        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    public static String removelastcharacter(String str) {
        String result = null;
        if((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }
    @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public HashMap<String, String> validateResetToken(Model modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setStatus("Active");
            userRepository.save(user);
            modelAndView.addAttribute("user", user);
            modelAndView.addAttribute("emailId", user.getEmail());
            HashMap<String, String> map = new HashMap<>();
            map.put("email", user.getEmail());
            map.put("name", user.getUsername());
            map.put("message", "resetPassword");



            return map;

        } else {

            HashMap<String, String> map = new HashMap<>();
            map.put("message", "The link is invalid or broken!");
            return map;
        }


    }

    /**
     * Receive the token from the link sent via email and display form to reset password
     */
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetUserPassword(Model modelAndView, @RequestBody User user) {
        // ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        System.out.println(user.getEmail());
        if(user.getEmail() != null) {
            // use email to find user
            User tokenUser = userRepository.findByEmailIgnoreCase(user.getEmail());
            tokenUser.setStatus("Active");
            tokenUser.setPassword(encoder.encode(user.getPassword()));
            // System.out.println(tokenUser.getPassword());
            userRepository.save(tokenUser);
            modelAndView.addAttribute("message", "Password successfully reset. You can now log in with the new credentials.");
            return "successResetPassword";
        } else {
            modelAndView.addAttribute("message","The link is invalid or broken!");
            return "error";
        }


    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ConfirmationTokenRepository getConfirmationTokenRepository() {
        return confirmationTokenRepository;
    }

    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
//    @RequestMapping(value = { "/userImage" }, method = RequestMethod.GET)
//    public void userImage(HttpServletRequest request, HttpServletResponse response, Model model,
//                          @RequestParam("code") String code) throws IOException {
//        User product = null;
//        if (code != null) {
//            product = userService.findByEmplid(code);
//        }
//        if (product != null && product.getImage() != null) {
//            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//            response.getOutputStream().write(product.getImage());
//        }
//        response.getOutputStream().close();
//
//    }
//    @RequestMapping(value = { "/client" }, method = RequestMethod.GET)
//    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
//        UserForm clientForm = null;
//
//
//        if (code != null && code.length() > 0) {
//            User product = userService.findByEmplid(code);
//            if (product != null) {
//                clientForm = new UserForm(product);
//            }
//        }
//        if (clientForm == null) {
//            clientForm = new UserForm();
//        }
//        model.addAttribute("clientForm", clientForm);
//        model.addAttribute("emplid", code);
//        return "client";
//    }

}