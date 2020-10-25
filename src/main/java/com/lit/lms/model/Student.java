package com.lit.lms.model;

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
@Table(name = "student")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "sId")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sId;

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

    private String fname;
    private String sname;
    private String city;
    private String country;
    private String email;
    private String customdata;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    @OneToMany(mappedBy = "assignment")
    private Set<StudentAssignment> studentAssignment = new HashSet<StudentAssignment>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getCustomdata() {
        return customdata;
    }

    public void setCustomdata(String customdata) {
        this.customdata = customdata;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Set<StudentAssignment> getStudentAssignment() {
        return studentAssignment;
    }

    public void setStudentAssignment(Set<StudentAssignment> studentAssignment) {
        this.studentAssignment = studentAssignment;
    }

    public void addStudentAssignment(StudentAssignment studentAssignment){
        this.studentAssignment.add(studentAssignment);
    }

    public Student(String fname, String sname, String city, String country, String email, List<Course> courses, Set<StudentAssignment> studentAssignment) {
        this.fname = fname;
        this.sname = sname;
        this.city = city;
        this.country = country;
        this.email = email;
        this.courses = courses;
        this.studentAssignment = studentAssignment;
    }

    public Student(){}

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +

                ", name='" + fname + '\'' +
                '}';
    }
}
