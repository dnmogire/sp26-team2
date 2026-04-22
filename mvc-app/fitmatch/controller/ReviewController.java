package com.example.fitmatch.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitmatch.model.Review;
import com.example.fitmatch.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // POST /api/reviews
    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Map<String, String> body) {
        Long bookingId = Long.parseLong(body.get("bookingId"));
        Integer rating = Integer.parseInt(body.get("rating"));
        String text = body.get("text");
        return ResponseEntity.ok(reviewService.createReview(bookingId, rating, text));
    }

    // GET /api/reviews/trainer/{trainerId}
    @GetMapping("/trainer/{trainerId}")
    public List<Review> getByTrainer(@PathVariable Long trainerId) {
        return reviewService.getByTrainer(trainerId);
    }

    // GET /api/reviews
    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    // PUT /api/reviews/{id}/reply
    @PutMapping("/{id}/reply")
    public ResponseEntity<Review> reply(@PathVariable Long id,
                                         @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(reviewService.replyToReview(id, body.get("reply")));
    }

    // PUT /api/reviews/{id}/edit-reply
    @PutMapping("/{id}/edit-reply")
    public ResponseEntity<Review> editReply(@PathVariable Long id,
                                             @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(reviewService.editReply(id, body.get("reply")));
    }

    // DELETE /api/reviews/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}