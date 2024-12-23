package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.Email;
import com.enterprisemanager.backend.domain.entities.Phone;

public interface IEmailService {
    Email save(Email email);
    Optional<Email> findById(Long id);
    List<Email> findAll();
    List<Email> findAllByCustomerId(String id);
    Optional<Email> update(Long id, Email email);
    Optional<Email> delete(Long id);
}
