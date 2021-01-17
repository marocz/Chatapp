package com.chatproject.marocz.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Entity
@Table(name = "chatmessage")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ChatMessage {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    private MessageStatus status;



    public ChatMessage(String chatId, String senderId, String recipientId, String senderName, String recipientName, String content,Date timestamp, MessageStatus status) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;

    }

    public ChatMessage(){}


}
