package com.lit.lms.dto;

/**
 * Created by kashifroshen on 11/3/17.
 */
public class RegisterDTO {


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private String username;
    private String password;



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String fname;
    String sname;
    String city;
    String country;
    String email;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public RegisterDTO(String fname, String sname, String city, String country, String email) {
//        this.fname = fname;
//        this.sname = sname;
//        this.email = email;
//        this.city = city;
//        this.country = country;
//
//    }

    public RegisterDTO() {

    }
}
