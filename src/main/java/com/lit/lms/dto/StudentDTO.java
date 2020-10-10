package com.lit.lms.dto;

/**
 * Created by kashifroshen on 11/3/17.
 */
public class StudentDTO {
    Long studentId;

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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public StudentDTO(String fname, ,String email) {
//        this.fname = fname;
//        this.email = email;
//    }

    public StudentDTO() {

    }
}
