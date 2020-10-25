package com.lit.lms.model;
import javax.persistence.*;
import java.util.Set;



@Entity
@Table(name = "reviews")
public class Reviews {


    public Long getMcId() {
        return mcId;
    }

    public void setMcId(Long mcId) {
        this.mcId = mcId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAuthorimage() {
        return authorimage;
    }

    public void setAuthorimage(String authorimage) {
        this.authorimage = authorimage;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }
    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mcId;

    private String firstname;
    private String surname;
    private String authorimage;
    private Long cId;
    private String comments;
    private String rating;
    private Long sId;


}
