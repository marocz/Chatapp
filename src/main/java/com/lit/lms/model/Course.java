package com.lit.lms.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Entity
@Table(name = "course")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "cId")
public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;

    private String name;
    private String description;
    private String title;
    private String imgurl;

    private String curl;
    private String status;
    private int rating;
    private Long price;
    private Long tcreator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    private int studentscount;
  
    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "cId"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "sId"))
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "modules_id")
    private Modules modules;

    @ManyToOne
    @JoinColumn(name = "reviews_id")
    private Reviews reviews;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Assignment> assignments;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "teacher_course", joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "cId"), inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "tId"))
    private Set<Teacher> teachers;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course() {
        this.students = new HashSet<>();
        this.teachers = new HashSet<>();
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Department getDepartment() {



        return department;
    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }


    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getStudentscount() {
        return studentscount;
    }

    public void setStudentscount(int studentscount) {
        this.studentscount = studentscount;
    }

    public Long getTcreator() {
        return tcreator;
    }

    public void setTcreator(Long tcreator) {
        this.tcreator = tcreator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", students=" + students +
                '}';
    }
}
