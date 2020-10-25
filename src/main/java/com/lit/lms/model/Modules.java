package com.lit.lms.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Represents the teacher entity
 *
 * Created by dinukshakandasamanage on 9/22/17.
 */

@Entity
@Table(name = "modules")
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "modules")
    private Course courses;

    private String title;
    private String contenturl;
    private String description;
    private Long tcreator;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", nullable = true)
    private Date createDate;



    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long mid) {
        this.mId = mid;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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


    public Modules() {

    }


    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }
}
