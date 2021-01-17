package com.chatproject.marocz.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Entity
@Table(name = "chatnotification")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ChatNotification {


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    private String senderId;
    private String senderName;


    public ChatNotification(Long id, String senderId, String sendername) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = sendername;

    }

    public ChatNotification(){}


}
