package com.lit.lms.dto;

/**
 * Created by kashifroshen on 11/1/17.
 */
public class TeacherDTO {
    String surname;
    String firstname;
    String email;
    String imgurl;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String name) {
        this.surname = surname;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
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

    public TeacherDTO() {

    }
}
