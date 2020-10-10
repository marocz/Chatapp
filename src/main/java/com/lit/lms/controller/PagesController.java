package com.lit.lms.controller;

import com.lit.lms.entities.*;
import com.lit.lms.repository.*;
import com.lit.lms.services.*;
import com.lit.lms.dto.*;
import com.lit.lms.model.*;
import javax.servlet.ServletException;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.Header;
import com.google.gson.Gson;
import com.lit.lms.services.core.*;


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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import org.json.JSONObject;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PagesController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private QuizService quizService;

    @Autowired
    private PaystackInline paystackInline;


    @GetMapping(value = {"/allcourses"})
    public String courses(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();

        model.addAttribute("allcourses", courseService.getAllCoursesDTO());
        model.addAttribute("alldepts", departmentService.getAllDepartments());
        model.addAttribute("deptname", "All Courses");


        return "courses";
    }
    @GetMapping(value = {"/acourses"})
    public String acourses(Model model,  @RequestParam(value = "dept") int dept) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();

        String id = String.valueOf(dept) ;
        Department depts = departmentService.getCourse(id);

        model.addAttribute("allcourses", courseService.getaCourse(dept));
        model.addAttribute("alldepts", departmentService.getAllDepartments());
        model.addAttribute("depts", departmentService.getCourse(id));
        model.addAttribute("deptname", depts.getName());

        return "courses";
    }
    @GetMapping(value = {"/register"})
    public String registration(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "register";
    }
    @PostMapping(value = {"/register"})
    public String register(Model model, @ModelAttribute("userForm") RegisterDTO userForm) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();

        studentService.createStudent(userForm);



        return "redirect:/login";
    }

    @GetMapping(value = {"/1mspowerbi"})
    public String mspowerbi(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "1mspowerbi";
    }

    @GetMapping(value = {"/createpowerbia"})
    public String mspowerbiall(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/createpowerbia";
    }

    @GetMapping(value = {"/publishpowerbia"})
    public String mspowerbipublish(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/publishpowerbia";
    }


    @GetMapping(value = {"/servicepowerbia"})
    public String mspowerbionlineserv(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/servicepowerbia";
    }

    @GetMapping(value = {"/mobilepowerbia"})
    public String mspowerbimobile(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();
        int i = 1;

        Long qid = new Long(i);
        Long qnid = new Long(i);

        Question quiz = quizService.getQuestionById(qid, qnid);
        System.out.println(quiz);

        model.addAttribute("myQuestions", quiz);

        return "mspowerbi/mobilepowerbia";
    }
    @GetMapping(value = {"/getdatapowerbia"})
    public String mspowerbigetdata(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/getdatapowerbia";
    }
    @GetMapping(value = {"/queryeditorpowerbia"})
    public String mspowerbiqeditor(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/queryeditorpowerbia";
    }

    @GetMapping(value = {"/combinedatapowerbia"})
    public String mspowerbicombined(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/combinedatapowerbia";
    }

    @GetMapping(value = {"/Extractcharpowerbia"})
    public String mspowerbiextractChar(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Extractcharpowerbia";
    }

    @GetMapping(value = {"/Removecolpowerbia"})
    public String mspowerbiremovecol(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Removecolpowerbia";
    }

    @GetMapping(value = {"/Splitcolpowerbia"})
    public String mspowerbisplitcol(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Splitcolpowerbia";
    }

    @GetMapping(value = {"/Replacedatapowerbia"})
    public String mspowerbireplacedata(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Replacedatapowerbia";
    }

    @GetMapping(value = {"/Trimdatapowerbia"})
    public String mspowerbitrimdata(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Trimdatapowerbia";
    }

    @GetMapping(value = {"/Powervisualpowerbia"})
    public String mspowerbipowervisual(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Powervisualpowerbia";
    }
    @GetMapping(value = {"/Pivotdatapowerbia"})
    public String mspowerbipivotdata(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Pivotdatapowerbia";
    }
    @GetMapping(value = {"/Powermappowerbia"})
    public String mspowerbipowermap(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "mspowerbi/Powermappowerbia";
    }

    @GetMapping(value = {"/enroll"})
    public String enroll(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "enroll";
    }
    @PostMapping(value = {"/enroll"})
    public JSONObject penroll(Model model, @ModelAttribute("PayModel") PaystackdataM PayModel ) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();

        String ref = "T896479883010922";
        int amount = PayModel.getAmount();
        String email = PayModel.getEmail();
        String plan = "";
        String callback = "localhost:8082/verify";

        return paystackInline.paystackStandard( ref, amount, email, plan, callback);


    }

    @GetMapping(value = {"/verify"})
    public String vero(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "enroll";
    }
    @GetMapping(value = {"/student"})
    public String student(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String logname = loggedInUser.getName();
        // User users = userService.findByUsernameIgnoreCase(logname);
        // String id = users.getEmplid();



        return "Students";
    }

    @GetMapping(value = {"/verifypayment/{paymentRef}"})
    protected void paymentResponseVerification(HttpServletRequest request, HttpServletResponse response, @PathVariable String paymentRef) throws ServletException, IOException
    {
        //Create this servlet to revieve the ajax request shown in the javascript file


        CloseableHttpClient client = HttpClients.createDefault();

        try
        {

            HttpGet newRequest = new HttpGet("https://api.paystack.co/transaction/verify/" + paymentRef);
            newRequest.addHeader("Content-type", "application/json");
            newRequest.addHeader("Authorization", "Bearer " + PayStackData.MY_PAY_STACK_SECRET_KEY);
            newRequest.addHeader("Cache-Control", "no-cache");
            CloseableHttpResponse newResponse = client.execute(newRequest);
            org.apache.http.HttpEntity entity = newResponse.getEntity();
            StringBuffer jb = new StringBuffer();
            String line = null;
            if(entity != null)
                try
                {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
                    while((line = rd.readLine()) != null)
                        jb.append(line);
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e);
                }
            else
                throw new Exception("Error Occured while connecting to paystack url");

            Gson g = new Gson(); //Instantiating the gson class
            VerifyPayStackTransaction payRes = g.fromJson(jb.toString(), VerifyPayStackTransaction.class);
            if(payRes == null || payRes.getStatus().equals("false"))
                throw new Exception("An error occurred while verifying payment");
            else if(payRes.getData().getStatus().equals("success"));//Check if payment was successful
//                try
//                {
//                    boolean validateTrans = DonationDAO.donationVerification(email, transactionId);
//                    boolean insertTransDetails = TransactionDAO.insertTransactionDetails(email, donCategory, amount, transactionId, paymentRef);
//                    if(validateTrans == false || insertTransDetails == false) throw new Exception("There was an error somewhere");
//
//                    //Transaction was successfull if you got here.....so have fun !!!
//                }
//                catch(Exception xcp)
//                {
//                    throw new RuntimeException(xcp);
//                }
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}