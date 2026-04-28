package com.example.fitmatch.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainer;

    private Integer rating;

    @Column(length = 1000)
    private String text;

    private LocalDateTime createdAt;

    @Column(length = 1000)
    private String trainerReply;

    private LocalDateTime replyAt;
    private LocalDateTime replyEditDeadline;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }
    public TrainerProfile getTrainer() { return trainer; }
    public void setTrainer(TrainerProfile trainer) { this.trainer = trainer; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getTrainerReply() { return trainerReply; }
    public void setTrainerReply(String trainerReply) { this.trainerReply = trainerReply; }
    public LocalDateTime getReplyAt() { return replyAt; }
    public void setReplyAt(LocalDateTime replyAt) { this.replyAt = replyAt; }
    public LocalDateTime getReplyEditDeadline() { return replyEditDeadline; }
    public void setReplyEditDeadline(LocalDateTime replyEditDeadline) { this.replyEditDeadline = replyEditDeadline; }
}
