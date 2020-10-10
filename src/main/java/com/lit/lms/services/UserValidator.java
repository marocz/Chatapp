package com.lit.lms.services;


import com.lit.lms.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    
    
    	String	SizeuserFormusername = "Please use between 6 and 32 characters.";
    	String	DuplicateuserFormusername = "Someone already has that username.";
    	String	SizeuserFormpassword = "Try one with at least 8 characters.";
    	String	DiffuserFormpasswordConfirm = "These passwords don't match.";
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", SizeuserFormusername);
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", DuplicateuserFormusername);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", SizeuserFormpassword);
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", DiffuserFormpasswordConfirm);
        }
    }
    
}