package com.example.fitmatch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitmatch.model.Service;
import com.example.fitmatch.service.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getAllActive() {
        return serviceService.getAllActive();
    }

    @GetMapping("/trainer/{trainerId}")
    public List<Service> getByTrainer(@PathVariable Long trainerId) {
        return serviceService.getByTrainer(trainerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getById(@PathVariable Long id) {
        return serviceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/trainer/{trainerId}")
    public ResponseEntity<Service> create(@PathVariable Long trainerId,
                                           @RequestBody Service service) {
        return ResponseEntity.ok(serviceService.createService(trainerId, service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable Long id,
                                           @RequestBody Service updated) {
        return ResponseEntity.ok(serviceService.updateService(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Service> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.deactivateService(id));
    }
}