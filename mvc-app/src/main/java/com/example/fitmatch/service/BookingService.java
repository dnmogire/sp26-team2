package com.example.fitmatch.service;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.model.User;
import com.example.fitmatch.repository.BookingRepository;
import com.example.fitmatch.repository.ServiceRepository;
import com.example.fitmatch.repository.TrainerProfileRepository;
import com.example.fitmatch.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;
    private final TrainerProfileRepository profileRepo;
    private final ServiceRepository serviceRepo;

    public BookingService(BookingRepository bookingRepo, UserRepository userRepo,
                          TrainerProfileRepository profileRepo, ServiceRepository serviceRepo) {
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.serviceRepo = serviceRepo;
    }

    public Booking createBooking(Long clientId, Long trainerId, Long serviceId,
                                 LocalDate date, LocalTime time) {
        User client = userRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));
        TrainerProfile trainer = profileRepo.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found: " + trainerId));
        com.example.fitmatch.model.Service service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found: " + serviceId));

        Booking booking = new Booking();
        booking.setClient(client);
        booking.setTrainer(trainer);
        booking.setService(service);
        booking.setDate(date);
        booking.setTime(time);
        booking.setStatus(Booking.Status.PENDING);
        return bookingRepo.save(booking);
    }

    public Booking confirmBooking(Long bookingId) {
        return updateStatus(bookingId, Booking.Status.CONFIRMED);
    }

    public Booking declineBooking(Long bookingId) {
        return updateStatus(bookingId, Booking.Status.DECLINED);
    }

    public Booking rescheduleBooking(Long bookingId, LocalDate newDate, LocalTime newTime) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));
        booking.setStatus(Booking.Status.RESCHEDULED);
        booking.setProposedNewDate(newDate);
        booking.setProposedNewTime(newTime);
        return bookingRepo.save(booking);
    }

    public Booking markComplete(Long bookingId) {
        return updateStatus(bookingId, Booking.Status.COMPLETED);
    }

    private Booking updateStatus(Long bookingId, Booking.Status status) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));
        booking.setStatus(status);
        return bookingRepo.save(booking);
    }

    public List<Booking> getByClient(Long clientId) {
        return bookingRepo.findByClientId(clientId);
    }

    public List<Booking> getByTrainer(Long trainerId) {
        return bookingRepo.findByTrainerId(trainerId);
    }

    public List<Booking> getByTrainerAndStatus(Long trainerId, Booking.Status status) {
        return bookingRepo.findByTrainerIdAndStatus(trainerId, status);
    }

    public List<Booking> getCompletedByClient(Long clientId) {
        return bookingRepo.findByClientIdAndStatus(clientId, Booking.Status.COMPLETED);
    }

    public Optional<Booking> getById(Long id) {
        return bookingRepo.findById(id);
    }
}
