package com.enterprisemanager.backend.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enterprisemanager.backend.application.services.ISupplyService;
import com.enterprisemanager.backend.domain.entities.Supply;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supply")
public class SupplyController {
    @Autowired
    private ISupplyService supplyService;

    @GetMapping
    public List<Supply> list() {
        return supplyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Supply> supplyOptional = supplyService.findById(id);
        if(supplyOptional.isPresent()){
            return ResponseEntity.ok(supplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Supply supply){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.save(supply));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Supply supply, @PathVariable Long id) {
        Optional<Supply> supplyOptional = supplyService.update(id, supply);
        if (supplyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(supplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Supply> supplyOptional = supplyService.delete(id);
        if (supplyOptional.isPresent()) {
            return ResponseEntity.ok(supplyOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}