package com.enterprisemanager.backend.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisemanager.backend.application.services.IEmailTypeService;
import com.enterprisemanager.backend.domain.entities.EmailType;

@RestController
@RequestMapping("/emailType")
public class EmailTypeController {
@Autowired
    private IEmailTypeService emailTypeService;

    @GetMapping
    public List<EmailType> list() {
        return emailTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<EmailType> emailTypeOptional = emailTypeService.findById(id);
        if(emailTypeOptional.isPresent()){
            return ResponseEntity.ok(emailTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody EmailType emailType){
        return ResponseEntity.status(HttpStatus.CREATED).body(emailTypeService.save(emailType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody EmailType emailType, @PathVariable Long id) {
        Optional<EmailType> emailTypeOptional = emailTypeService.update(id, emailType);
        if (emailTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(emailTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<EmailType> emailTypeOptional = emailTypeService.delete(id);
        if (emailTypeOptional.isPresent()) {
            return ResponseEntity.ok(emailTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}