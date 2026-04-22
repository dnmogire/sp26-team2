package com.example.fitmatch.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainer;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    private LocalDate date;
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDate proposedNewDate;
    private LocalTime proposedNewTime;
    private LocalDateTime createdAt;

    public enum Status { PENDING, CONFIRMED, DECLINED, COMPLETED, RESCHEDULED }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }
    public TrainerProfile getTrainer() { return trainer; }
    public void setTrainer(TrainerProfile trainer) { this.trainer = trainer; }
    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDate getProposedNewDate() { return proposedNewDate; }
    public void setProposedNewDate(LocalDate proposedNewDate) { this.proposedNewDate = proposedNewDate; }
    public LocalTime getProposedNewTime() { return proposedNewTime; }
    public void setProposedNewTime(LocalTime proposedNewTime) { this.proposedNewTime = proposedNewTime; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}