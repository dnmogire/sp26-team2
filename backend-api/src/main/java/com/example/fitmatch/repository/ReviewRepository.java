package com.example.fitmatch.repository;

import com.example.fitmatch.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTrainerId(Long trainerId);
    Optional<Review> findByBookingId(Long bookingId);
}