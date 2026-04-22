package com.example.fitmatch.service;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.model.Review;
import com.example.fitmatch.repository.BookingRepository;
import com.example.fitmatch.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final BookingRepository bookingRepo;
    private final TrainerProfileService profileService;

    public ReviewService(ReviewRepository reviewRepo, BookingRepository bookingRepo,
                         TrainerProfileService profileService) {
        this.reviewRepo = reviewRepo;
        this.bookingRepo = bookingRepo;
        this.profileService = profileService;
    }

    public Review createReview(Long bookingId, Integer rating, String text) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));
        if (booking.getStatus() != Booking.Status.COMPLETED) {
            throw new RuntimeException("Can only review a completed booking");
        }
        if (reviewRepo.findByBookingId(bookingId).isPresent()) {
            throw new RuntimeException("Review already exists for this booking");
        }
        Review review = new Review();
        review.setBooking(booking);
        review.setClient(booking.getClient());
        review.setTrainer(booking.getTrainer());
        review.setRating(rating);
        review.setText(text);
        Review saved = reviewRepo.save(review);

        List<Integer> allRatings = reviewRepo.findByTrainerId(booking.getTrainer().getId())
                .stream().map(Review::getRating).collect(Collectors.toList());
        profileService.recalculateAvgRating(booking.getTrainer().getId(), allRatings);
        return saved;
    }

    public Review replyToReview(Long reviewId, String replyText) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found: " + reviewId));
        review.setTrainerReply(replyText);
        review.setReplyAt(LocalDateTime.now());
        review.setReplyEditDeadline(LocalDateTime.now().plusHours(48));
        return reviewRepo.save(review);
    }

    public Review editReply(Long reviewId, String replyText) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found: " + reviewId));
        if (review.getReplyEditDeadline() == null ||
                LocalDateTime.now().isAfter(review.getReplyEditDeadline())) {
            throw new RuntimeException("Edit window (48 hours) has passed");
        }
        review.setTrainerReply(replyText);
        return reviewRepo.save(review);
    }

    public List<Review> getByTrainer(Long trainerId) {
        return reviewRepo.findByTrainerId(trainerId);
    }

    public Optional<Review> getByBooking(Long bookingId) {
        return reviewRepo.findByBookingId(bookingId);
    }

    public List<Review> getAll() {
        return reviewRepo.findAll();
    }

    public void deleteReview(Long reviewId) {
        reviewRepo.deleteById(reviewId);
    }
}