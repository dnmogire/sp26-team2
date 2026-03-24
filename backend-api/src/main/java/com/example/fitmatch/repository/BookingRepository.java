package com.example.fitmatch.repository;

import com.example.fitmatch.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClientId(Long clientId);
    List<Booking> findByTrainerId(Long trainerId);
    List<Booking> findByTrainerIdAndStatus(Long trainerId, Booking.Status status);
    List<Booking> findByClientIdAndStatus(Long clientId, Booking.Status status);
}