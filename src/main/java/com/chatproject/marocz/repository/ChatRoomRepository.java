package com.chatproject.marocz.repository;

import com.chatproject.marocz.model.ChatMessage;
import com.chatproject.marocz.model.ChatRoom;
import com.chatproject.marocz.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Consists all the student related CRUD operations done to the DB
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {
    List<ChatRoom> findBySenderIdAndRecipientId(
            String senderId, String recipientId);


}
