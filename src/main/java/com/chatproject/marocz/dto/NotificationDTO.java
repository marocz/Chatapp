package com.chatproject.marocz.dto;

public class NotificationDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    private Long id;
    private String title;
    private String text;
    private String date;
    private String image;
    private String email;
    private String status;


    public NotificationDTO(String username, String phone, String date, String image, String email) {
        this.title = username;
        this.text = phone;
        this.date = date;
        this.image = image;
        this.email = email;

    }

    public NotificationDTO(){}


}