package com.chatproject.marocz.dto;

public class AdminDTO {

    private Long tId;
    private String surname;
    private String firstname;
    private String email;
    private String imgurl;

    private String acustomdata;

    private String username;
    private String password;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }



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
    public String getAcustomdata() {
        return acustomdata;
    }

    public void setAcustomdata(String acustomdata) {
        this.acustomdata = acustomdata;
    }
    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }


    public AdminDTO() {

    }


}
