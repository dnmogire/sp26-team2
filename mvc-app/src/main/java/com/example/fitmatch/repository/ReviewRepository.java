package com.example.fitmatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitmatch.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTrainerId(Long trainerId);
    List<Review> findByClientId(Long clientId);
    Optional<Review> findByBookingId(Long bookingId);
}
