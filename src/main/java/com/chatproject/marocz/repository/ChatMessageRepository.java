package com.chatproject.marocz.repository;

import com.chatproject.marocz.model.ChatMessage;
import com.chatproject.marocz.model.MessageStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Consists all the student related CRUD operations done to the DB
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
    List<ChatMessage> findBySenderIdAndRecipientId(
            String senderId, String recipientId);

}
