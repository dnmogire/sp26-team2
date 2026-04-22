package com.example.fitmatch.service;

import com.example.fitmatch.model.Service;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.repository.ServiceRepository;
import com.example.fitmatch.repository.TrainerProfileRepository;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepo;
    private final TrainerProfileRepository profileRepo;

    public ServiceService(ServiceRepository serviceRepo, TrainerProfileRepository profileRepo) {
        this.serviceRepo = serviceRepo;
        this.profileRepo = profileRepo;
    }

    public Service createService(Long trainerId, Service service) {
        TrainerProfile profile = profileRepo.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found: " + trainerId));
        service.setTrainer(profile);
        service.setIsActive(true);
        return serviceRepo.save(service);
    }

    public Service updateService(Long serviceId, Service updated) {
        Service existing = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found: " + serviceId));
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getDuration() != null) existing.setDuration(updated.getDuration());
        if (updated.getPrice() != null) existing.setPrice(updated.getPrice());
        if (updated.getCategory() != null) existing.setCategory(updated.getCategory());
        return serviceRepo.save(existing);
    }

    public Service deactivateService(Long serviceId) {
        Service existing = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found: " + serviceId));
        existing.setIsActive(false);
        return serviceRepo.save(existing);
    }

    public List<Service> getByTrainer(Long trainerId) {
        return serviceRepo.findByTrainerId(trainerId);
    }

    public List<Service> getActiveByTrainer(Long trainerId) {
        return serviceRepo.findByTrainerIdAndIsActiveTrue(trainerId);
    }

    public List<Service> getAllActive() {
        return serviceRepo.findByIsActiveTrue();
    }

    public Optional<Service> getById(Long id) {
        return serviceRepo.findById(id);
    }
}