package com.chatproject.marocz.repository;

import com.chatproject.marocz.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findByEmail(String email);

}
