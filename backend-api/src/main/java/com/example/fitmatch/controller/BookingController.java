package com.example.fitmatch.controller;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // POST /api/bookings
    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Map<String, String> body) {
        Long clientId = Long.parseLong(body.get("clientId"));
        Long trainerId = Long.parseLong(body.get("trainerId"));
        Long serviceId = Long.parseLong(body.get("serviceId"));
        LocalDate date = LocalDate.parse(body.get("date"));
        LocalTime time = LocalTime.parse(body.get("time"));
        return ResponseEntity.ok(bookingService.createBooking(clientId, trainerId, serviceId, date, time));
    }

    // GET /api/bookings/trainer/{trainerId}
    @GetMapping("/trainer/{trainerId}")
    public List<Booking> getByTrainer(@PathVariable Long trainerId) {
        return bookingService.getByTrainer(trainerId);
    }

    // GET /api/bookings/client/{clientId}
    @GetMapping("/client/{clientId}")
    public List<Booking> getByClient(@PathVariable Long clientId) {
        return bookingService.getByClient(clientId);
    }

    // PUT /api/bookings/{id}/confirm
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirm(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    // PUT /api/bookings/{id}/decline
    @PutMapping("/{id}/decline")
    public ResponseEntity<Booking> decline(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.declineBooking(id));
    }

    // PUT /api/bookings/{id}/complete
    @PutMapping("/{id}/complete")
    public ResponseEntity<Booking> complete(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.markComplete(id));
    }

    // PUT /api/bookings/{id}/reschedule
    @PutMapping("/{id}/reschedule")
    public ResponseEntity<Booking> reschedule(@PathVariable Long id,
                                               @RequestBody Map<String, String> body) {
        LocalDate newDate = LocalDate.parse(body.get("date"));
        LocalTime newTime = LocalTime.parse(body.get("time"));
        return ResponseEntity.ok(bookingService.rescheduleBooking(id, newDate, newTime));
    }
}