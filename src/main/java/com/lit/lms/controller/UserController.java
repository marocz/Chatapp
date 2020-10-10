package com.lit.lms.controller;

import com.lit.lms.entities.*;
import com.lit.lms.repository.*;
import com.lit.lms.services.*;
import com.sipios.springsearch.anotation.SearchSpec;

import com.lit.lms.model.UserForm;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.*;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserRepository userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AuthenticationManager authenticationManager;




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
    @GetMapping(value = {"/mentee"})
    public String mentee(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        model.addAttribute("username", logname);


        return "mentee";
    }
    //    @GetMapping(value = {"/","/login","loginpage"})
//    public String loginin(Model model) {
//
//        return "loginpage";
//    }
    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }

    @RequestMapping("/category")
    public String category() {
        return "category";
    }

    @RequestMapping("/product")
    public String product() {
        return "product";
    }


    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("/checkout")
    public String checkouts() {
        return "checkout";
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