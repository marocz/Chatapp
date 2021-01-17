package com.chatproject.marocz.controller;

import com.chatproject.marocz.dto.Profile2DTO;
import com.chatproject.marocz.dto.String1DTO;
import com.chatproject.marocz.model.ChatMessage;
import com.chatproject.marocz.model.ChatNotification;
import com.chatproject.marocz.repository.ProfileRepository;
import com.chatproject.marocz.repository.UserRepository;
import com.chatproject.marocz.services.ChatMessageService;
import com.chatproject.marocz.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import java.util.*;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    ProfileRepository profileRepository;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        Optional<String> chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<ChatMessage> findMessage ( @PathVariable String ids) {
        Long id = Long.parseLong(ids);
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
    @GetMapping("/users")
    public ResponseEntity<List<Profile2DTO>> findusers ( @PathVariable String ids) {
        Long id = Long.parseLong(ids);
    List<Profile2DTO> usernames = new ArrayList<>();
        profileRepository.findAll().forEach(users -> {
            Profile2DTO profile2DTO = new Profile2DTO();
            profile2DTO.setUsername(users.getUsername());
            profile2DTO.setUseravatar(users.getUseravatar());
            usernames.add(profile2DTO);
        });
        return ResponseEntity
                .ok(usernames);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return message;
    }

    @MessageMapping("addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSenderName());
        return message;
    }

}