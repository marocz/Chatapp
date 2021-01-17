package com.chatproject.marocz.dto;

public class ProfileDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUseravatar() {
        return userAvatar;
    }

    public void setUseravatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public int getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(int notifyCount) {
        this.notifyCount = notifyCount;
    }

    public int getInBasket() {
        return inBasket;
    }

    public void setInBasket(int inBasket) {
        this.inBasket = inBasket;
    }
    public String getUserAvatarInternet() {
        return userAvatarInternet;
    }

    public void setUserAvatarInternet(String userAvatarInternet) {
        this.userAvatarInternet = userAvatarInternet;
    }

    private String userAvatarInternet;


    private Long id;


    private String username;
    private String phone;
    private String userAvatar;
    private String gender;
    private String email;



    private String dateofbirth;

    private int notifyCount;
    private int inBasket;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

}