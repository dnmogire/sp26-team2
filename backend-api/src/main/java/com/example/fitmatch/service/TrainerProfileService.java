package com.example.fitmatch.service;

import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.model.User;
import com.example.fitmatch.repository.TrainerProfileRepository;
import com.example.fitmatch.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerProfileService {

    private final TrainerProfileRepository profileRepo;
    private final UserRepository userRepo;

    public TrainerProfileService(TrainerProfileRepository profileRepo, UserRepository userRepo) {
        this.profileRepo = profileRepo;
        this.userRepo = userRepo;
    }

    public TrainerProfile createProfile(Long userId, TrainerProfile profile) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        profile.setUser(user);
        profile.setIsActive(true);
        return profileRepo.save(profile);
    }

    public TrainerProfile updateProfile(Long profileId, TrainerProfile updated) {
        TrainerProfile existing = profileRepo.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found: " + profileId));
        if (updated.getBio() != null) existing.setBio(updated.getBio());
        if (updated.getCertifications() != null) existing.setCertifications(updated.getCertifications());
        if (updated.getSpecialties() != null) existing.setSpecialties(updated.getSpecialties());
        if (updated.getHourlyRate() != null) existing.setHourlyRate(updated.getHourlyRate());
        if (updated.getLocation() != null) existing.setLocation(updated.getLocation());
        if (updated.getPhone() != null) existing.setPhone(updated.getPhone());
        return profileRepo.save(existing);
    }

    public Optional<TrainerProfile> getById(Long id) {
        return profileRepo.findById(id);
    }

    public Optional<TrainerProfile> getByUserId(Long userId) {
        return profileRepo.findByUserId(userId);
    }

    public List<TrainerProfile> getAll() {
        return profileRepo.findByIsActiveTrue();
    }

    public List<TrainerProfile> searchByLocation(String location) {
        return profileRepo.findByLocationContainingIgnoreCaseAndIsActiveTrue(location);
    }

    public List<TrainerProfile> searchBySpecialty(String specialty) {
        return profileRepo.findBySpecialtiesContainingIgnoreCaseAndIsActiveTrue(specialty);
    }

    public List<TrainerProfile> searchByMaxRate(Double maxRate) {
        return profileRepo.findByHourlyRateLessThanEqualAndIsActiveTrue(maxRate);
    }

    public void recalculateAvgRating(Long trainerId, List<Integer> allRatings) {
        profileRepo.findById(trainerId).ifPresent(profile -> {
            double avg = allRatings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            profile.setAvgRating(Math.round(avg * 10.0) / 10.0);
            profileRepo.save(profile);
        });
    }
}