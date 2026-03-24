package com.example.fitmatch.repository;

import com.example.fitmatch.model.TrainerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrainerProfileRepository extends JpaRepository<TrainerProfile, Long> {
    Optional<TrainerProfile> findByUserId(Long userId);
    List<TrainerProfile> findByLocationContainingIgnoreCaseAndIsActiveTrue(String location);
    List<TrainerProfile> findBySpecialtiesContainingIgnoreCaseAndIsActiveTrue(String specialty);
    List<TrainerProfile> findByHourlyRateLessThanEqualAndIsActiveTrue(Double maxRate);
    List<TrainerProfile> findByIsActiveTrue();
}