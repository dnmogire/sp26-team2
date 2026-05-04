package com.example.fitmatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.model.Service;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.service.BookingService;
import com.example.fitmatch.service.ReviewService;
import com.example.fitmatch.service.ServiceService;
import com.example.fitmatch.service.TrainerProfileService;

@Controller
@RequestMapping("/provider")
public class TrainerMvcController {

    private final TrainerProfileService profileService;
    private final BookingService bookingService;
    private final ServiceService serviceService;
    private final ReviewService reviewService;

    public TrainerMvcController(TrainerProfileService profileService,
                                BookingService bookingService,
                                ServiceService serviceService,
                                ReviewService reviewService) {
        this.profileService = profileService;
        this.bookingService = bookingService;
        this.serviceService = serviceService;
        this.reviewService = reviewService;
    }

    // ─── Dashboard ───────────────────────────────────────────────────────────

    @GetMapping("/dashboard/{trainerId}")
    public String dashboard(@PathVariable Long trainerId, Model model) {
        TrainerProfile profile = profileService.getById(trainerId).orElse(null);
        model.addAttribute("profile", profile);
        model.addAttribute("pendingBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.PENDING));
        model.addAttribute("confirmedBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.CONFIRMED));
        model.addAttribute("completedBookings",
                bookingService.getByTrainerAndStatus(trainerId, Booking.Status.COMPLETED));
        model.addAttribute("reviews", reviewService.getByTrainer(trainerId));
        model.addAttribute("trainerId", trainerId);
        return "dashboard";
    }

    // ─── Profile ─────────────────────────────────────────────────────────────

    @GetMapping("/profile/{trainerId}")
    public String viewProfile(@PathVariable Long trainerId, Model model) {
        TrainerProfile profile = profileService.getById(trainerId).orElse(null);
        model.addAttribute("profile", profile);
        model.addAttribute("reviews", reviewService.getByTrainer(trainerId));
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

    // ─── Bookings ─────────────────────────────────────────────────────────────

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

    // ─── Services ─────────────────────────────────────────────────────────────

    @GetMapping("/services/{trainerId}")
    public String viewServices(@PathVariable Long trainerId, Model model) {
        model.addAttribute("services", serviceService.getByTrainer(trainerId));
        model.addAttribute("trainerId", trainerId);
        model.addAttribute("newService", new Service());
        return "services";
    }

    @PostMapping("/services/{trainerId}/add")
    public String addService(@PathVariable Long trainerId, Service service) {
        serviceService.createService(trainerId, service);
        return "redirect:/provider/services/" + trainerId;
    }

    @PostMapping("/services/{serviceId}/delete/{trainerId}")
    public String deleteService(@PathVariable Long serviceId, @PathVariable Long trainerId) {
        serviceService.deactivateService(serviceId);
        return "redirect:/provider/services/" + trainerId;
    }

    // ─── Reviews ─────────────────────────────────────────────────────────────

    @GetMapping("/reviews/{trainerId}")
    public String viewReviews(@PathVariable Long trainerId, Model model) {
        model.addAttribute("reviews", reviewService.getByTrainer(trainerId));
        model.addAttribute("trainerId", trainerId);
        return "reviews";
    }

    @PostMapping("/reviews/{reviewId}/reply/{trainerId}")
    public String replyToReview(@PathVariable Long reviewId,
                                @PathVariable Long trainerId,
                                @RequestParam String replyText) {
        reviewService.replyToReview(reviewId, replyText);
        return "redirect:/provider/reviews/" + trainerId;
    }
}