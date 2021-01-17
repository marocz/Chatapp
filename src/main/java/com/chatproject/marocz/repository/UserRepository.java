package com.chatproject.marocz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatproject.marocz.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
    User findByUsername(String username);
    User findByEmailIgnoreCase(String email);
    User findByEmail(String email);

}




