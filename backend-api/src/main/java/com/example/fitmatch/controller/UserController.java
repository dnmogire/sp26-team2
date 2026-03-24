package com.example.fitmatch.controller;

import com.example.fitmatch.model.User;
import com.example.fitmatch.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
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
}