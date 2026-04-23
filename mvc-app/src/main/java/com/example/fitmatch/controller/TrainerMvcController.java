package com.example.fitmatch.controller;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.service.BookingService;
import com.example.fitmatch.service.TrainerProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/provider")
public class TrainerMvcController {

    private final TrainerProfileService profileService;
    private final BookingService bookingService;

    public TrainerMvcController(TrainerProfileService profileService, BookingService bookingService) {
        this.profileService = profileService;
        this.bookingService = bookingService;
    }

    @GetMapping("/dashboard/{trainerId}")
    public String dashboard(@PathVariable Long trainerId, Model model) {
        TrainerProfile profile = profileService.getById(trainerId).orElse(null);
        model.addAttribute("profile", profile);
        model.addAttribute("pendingBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.PENDING));
        model.addAttribute("confirmedBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.CONFIRMED));
        model.addAttribute("trainerId", trainerId);
        return "dashboard";
    }

    @GetMapping("/profile/{trainerId}")
    public String viewProfile(@PathVariable Long trainerId, Model model) {
        TrainerProfile profile = profileService.getById(trainerId).orElse(null);
        model.addAttribute("profile", profile);
        model.addAttribute("trainerId", trainerId);
        return "profile";
    }

    @GetMapping("/profile/{trainerId}/edit")
    public String showEditForm(@PathVariable Long trainerId, Model model) {
        TrainerProfile profile = profileService.getById(trainerId).orElse(null);
        model.addAttribute("profile", profile);
        model.addAttribute("trainerId", trainerId);
        return "profile-edit";
    }

    @PostMapping("/profile/{trainerId}/edit")
    public String updateProfile(@PathVariable Long trainerId, TrainerProfile updated) {
        profileService.updateProfile(trainerId, updated);
        return "redirect:/provider/profile/" + trainerId;
    }

    @GetMapping("/bookings/{trainerId}")
    public String viewBookings(@PathVariable Long trainerId, Model model) {
        model.addAttribute("pendingBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.PENDING));
        model.addAttribute("confirmedBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.CONFIRMED));
        model.addAttribute("completedBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.COMPLETED));
        model.addAttribute("declinedBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.DECLINED));
        model.addAttribute("trainerId", trainerId);
        return "bookings";
    }

    @GetMapping("/bookings/{bookingId}/confirm/{trainerId}")
    public String confirmBooking(@PathVariable Long bookingId, @PathVariable Long trainerId) {
        bookingService.confirmBooking(bookingId);
        return "redirect:/provider/bookings/" + trainerId;
    }

    @GetMapping("/bookings/{bookingId}/decline/{trainerId}")
    public String declineBooking(@PathVariable Long bookingId, @PathVariable Long trainerId) {
        bookingService.declineBooking(bookingId);
        return "redirect:/provider/bookings/" + trainerId;
    }

    @GetMapping("/bookings/{bookingId}/complete/{trainerId}")
    public String completeBooking(@PathVariable Long bookingId, @PathVariable Long trainerId) {
        bookingService.markComplete(bookingId);
        return "redirect:/provider/bookings/" + trainerId;
    }
}