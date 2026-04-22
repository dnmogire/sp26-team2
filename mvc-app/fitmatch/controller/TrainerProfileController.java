package com.example.fitmatch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.service.TrainerProfileService;

@Controller
@RequestMapping("/api/trainers")
public class TrainerProfileController {

    private final TrainerProfileService profileService;

    public TrainerProfileController(TrainerProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<TrainerProfile> getAll() {
        return profileService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerProfile> getById(@PathVariable Long id) {
        return profileService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<TrainerProfile> getByUser(@PathVariable Long userId) {
        return profileService.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<TrainerProfile> create(@PathVariable Long userId,
                                                  @RequestBody TrainerProfile profile) {
        return ResponseEntity.ok(profileService.createProfile(userId, profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerProfile> update(@PathVariable Long id,
                                                  @RequestBody TrainerProfile updated) {
        return ResponseEntity.ok(profileService.updateProfile(id, updated));
    }

    @GetMapping("/search")
    public List<TrainerProfile> search(@RequestParam(required = false) String location,
                                       @RequestParam(required = false) String specialty,
                                       @RequestParam(required = false) Double maxRate) {
        if (location != null) return profileService.searchByLocation(location);
        if (specialty != null) return profileService.searchBySpecialty(specialty);
        if (maxRate != null) return profileService.searchByMaxRate(maxRate);
        return profileService.getAll();
    }
}