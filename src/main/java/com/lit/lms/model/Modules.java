package com.lit.lms.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents the teacher entity
 *
 * Created by dinukshakandasamanage on 9/22/17.
 */

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses;

    private String surname;
    private String firstname;
    private String email;
    private int rating;
    private String imgurl;
    private String reviewscount;


    public String getReviewscount() {
        return reviewscount;
    }

    public void setReviewscount(String reviewscount) {
        this.reviewscount = reviewscount;
    }



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
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Teacher(Department department, String surname, String firstname) {
        this.department = department;
        this.surname = surname;
        this.firstname = firstname;
    }

    public Teacher() {

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
