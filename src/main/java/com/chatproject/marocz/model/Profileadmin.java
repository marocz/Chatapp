package com.chatproject.marocz.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Entity
@Table(name = "profileadmin")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Profileadmin {

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

    public String getcurrentOrder() {
        return currentOrder;
    }

    public void setcurrentOrder(String currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getopenOrderOnMap() {
        return openOrderOnMap;
    }

    public void setOpenOrderOnMap (String openOrderOnMap) {
        this.openOrderOnMap = openOrderOnMap;
    }

    public int getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(int notifyCount) {
        this.notifyCount = notifyCount;
    }

    public String getBackRoute() {
        return backRoute;
    }

    public void setBackRoute(String backRoute) {
        this.backRoute = backRoute;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String phone;
    private String userAvatar;
    private String currentOrder;
    private String email;

    public String getBackRouteMap() {
        return backRouteMap;
    }

    public void setBackRouteMap(String backRouteMap) {
        this.backRouteMap = backRouteMap;
    }

    private String openOrderOnMap;



    private String backRoute;

    private int notifyCount;
    private String backRouteMap;

    public Profileadmin(String username, String phone, String useravatar, String openOrderOnMap, String email, int notifyCount, String backRoute, String backRouteMap) {
        this.username = username;
        this.phone = phone;
        this.userAvatar = useravatar;
        this.openOrderOnMap = openOrderOnMap;
        this.backRoute = backRoute;
        this.email = email;
        this.notifyCount = notifyCount;
        this.backRouteMap = backRouteMap;

    }

    public Profileadmin(){}


}
