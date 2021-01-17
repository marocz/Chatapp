package com.chatproject.marocz.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents the teacher entity
 *
 * Created by dinukshakandasamanage on 9/22/17.
 */

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tId;

    private String surname;
    private String firstname;
    private String email;
    private String imgurl;

    private String acustomdata;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return tId;
    }

    public void setId(Long id) {
        this.tId = id;
    }

    public String getSurname() {
        return surname;
    }
    public Admin( String surname, String firstname) {

        this.surname = surname;
        this.firstname = firstname;
    }

    public Admin() {

    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }



    public String getAcustomdata() {
        return acustomdata;
    }

    public void setAcustomdata(String acustomdata) {
        this.acustomdata = acustomdata;
    }

}
