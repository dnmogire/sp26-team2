package com.example.fitmatch.repository;

import com.example.fitmatch.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByTrainerId(Long trainerId);
    List<Service> findByTrainerIdAndIsActiveTrue(Long trainerId);
    List<Service> findByIsActiveTrue();
}
