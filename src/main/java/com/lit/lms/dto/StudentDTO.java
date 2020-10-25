package com.lit.lms.dto;

import com.lit.lms.model.Course;

import java.util.ArrayList;
import java.util.List;

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
    String customdata;
    List<CourseDTO> courses;



    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCustomdata() {
        return customdata;
    }

    public void setCustomdata(String customdata) {
        this.customdata = customdata;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> coursess) {

        List<CourseDTO> courseDTO = new ArrayList<>();
        coursess.forEach(course -> {CourseDTO assign = new CourseDTO();
            assign.setcId(course.getcId());
            assign.setCurl(course.getCurl());
            assign.setDeptname(course.getDepartment().getName());
            assign.setDeptdesc(course.getDepartment().getDescription());
            assign.setPrice(course.getPrice());
            assign.setRating(course.getRating());
            assign.setStudentcount(course.getStudentscount());
            assign.setTitle(course.getTitle());
            assign.setDescription(course.getDescription());

            courseDTO.add(assign); });
        this.courses = courseDTO;
    }

//    public StudentDTO(String fname, ,String email) {
//        this.fname = fname;
//        this.email = email;
//    }

    public StudentDTO() {

    }
}
