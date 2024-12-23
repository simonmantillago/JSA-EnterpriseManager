package com.enterprisemanager.backend.infrastructure.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisemanager.backend.application.services.IPhoneTypeService;
import com.enterprisemanager.backend.domain.entities.PhoneType;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/Phone_Type")
public class PhoneTypeController {
    @Autowired
    private IPhoneTypeService phoneTypeService;

    @GetMapping
    public List<PhoneType> list() {
        return phoneTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<PhoneType> phoneTypeOptional = phoneTypeService.findById(id);
        if(phoneTypeOptional.isPresent()){
            return ResponseEntity.ok(phoneTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody PhoneType phoneType, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneTypeService.save(phoneType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PhoneType phoneType, @PathVariable Long id, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
            }
        Optional<PhoneType> phoneTypeOptional = phoneTypeService.update(id, phoneType);
        if (phoneTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(phoneTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<PhoneType> phoneTypeOptional = phoneTypeService.delete(id);
        if (phoneTypeOptional.isPresent()) {
            return ResponseEntity.ok(phoneTypeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
        errors.put(err.getField(), "El campo " + err.getField() + " " +

        err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
        }
}