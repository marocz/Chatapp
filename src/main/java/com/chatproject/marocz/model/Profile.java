package com.chatproject.marocz.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Entity
@Table(name = "profile")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Profile{

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String phone;
    private String userAvatar;
    private String gender;
    private String email;

    public String getUserAvatarInternet() {
        return userAvatarInternet;
    }

    public void setUserAvatarInternet(String userAvatarInternet) {
        this.userAvatarInternet = userAvatarInternet;
    }

    private String userAvatarInternet;



    private String dateofbirth;

    private int notifyCount;
    private int inBasket;

    public Profile(String username, String phone, String useravatar, String gender, String email, int notifyCount, int inBasket, String userAvatarInternet) {
        this.username = username;
        this.phone = phone;
        this.userAvatar = useravatar;
        this.userAvatarInternet = userAvatarInternet;
        this.gender = gender;
        this.email = email;
        this.notifyCount = notifyCount;
        this.inBasket = inBasket;

    }

    public Profile(){}


}
