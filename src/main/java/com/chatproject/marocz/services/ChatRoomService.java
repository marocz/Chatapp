package com.chatproject.marocz.services;

import com.chatproject.marocz.dto.String1DTO;
import com.chatproject.marocz.model.ChatRoom;
import com.chatproject.marocz.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String recipientId, boolean createIfNotExist) {

        try{
            List<ChatRoom> chatRoom = chatRoomRepository
                    .findBySenderIdAndRecipientId(senderId, recipientId);
            Optional<String> chatID = Optional.empty();
            for (ChatRoom room : chatRoom){
                chatID = Optional.of(room.getChatId());

            }
            return chatID;

        }catch (Exception exception){

            if(!createIfNotExist) {
                return  Optional.empty();
            }

            String chatId =
                    String.format("%s_%s", senderId, recipientId);

            ChatRoom senderRecipient = new ChatRoom();
            senderRecipient.setChatId(chatId);
            senderRecipient.setRecipientId(recipientId);
            senderRecipient.setSenderId(senderId);


            ChatRoom recipientSender = new ChatRoom();
            recipientSender.setChatId(chatId);
            recipientSender.setRecipientId(recipientId);
            recipientSender.setSenderId(senderId);

            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);

            return Optional.of(chatId);
        }
    }
}