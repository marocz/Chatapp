package com.lit.lms.repository;

import org.springframework.data.repository.CrudRepository;

import com.lit.lms.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}