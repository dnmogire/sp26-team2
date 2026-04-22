package com.example.fitmatch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.model.User;
import com.example.fitmatch.repository.UserRepository;
import com.example.fitmatch.service.BookingService;
import com.example.fitmatch.service.TrainerProfileService;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;
    private final TrainerProfileService trainerProfileService;
    private final BookingService bookingService;

    public UserController(UserRepository userRepo, TrainerProfileService trainerProfileService, BookingService bookingService) {
        this.userRepo = userRepo;
        this.trainerProfileService = trainerProfileService;
        this.bookingService = bookingService;
    }

    // GET /api/users
    @GetMapping
    public List<User> getAll() {
        return userRepo.findAll();
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userRepo.save(user));
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updated) {
        return userRepo.findById(id).map(existing -> {
            if (updated.getFirstName() != null) existing.setFirstName(updated.getFirstName());
            if (updated.getLastName() != null) existing.setLastName(updated.getLastName());
            if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
            if (updated.getRole() != null) existing.setRole(updated.getRole());
            return ResponseEntity.ok(userRepo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/users/trainers/search?activity=     search trainer by activity
    @GetMapping("/trainers/search")
    public List <TrainerProfile> searchByActivity(@RequestParam String activity){
        return trainerProfileService.searchBySpecialty(activity);
    }

    //GET /api/users/trainers/filter?maxPrice=      filter by price
    @GetMapping("/trainers/filter")
    public List <TrainerProfile> filterByPrice(@RequestParam Double maxPrice){
        return trainerProfileService.searchByMaxRate(maxPrice);
    }


    //GET /api/users/{id}/bookings    booking history
    @GetMapping("/{id}/bookings")
    public List <Booking> getBookingHistory(@PathVariable Long id){
        return bookingService.getByClient(id);
    }

    //GET /api/users/trainers/location?location=
    @GetMapping("/trainers/location")
    public List <TrainerProfile> searchByLocation(@RequestParam String location){
        return trainerProfileService.searchByLocation(location);
    }
}