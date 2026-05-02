package com.example.fitmatch.mvc.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fitmatch.model.Booking;
import com.example.fitmatch.model.Review;
import com.example.fitmatch.model.Service;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.model.User;
import com.example.fitmatch.repository.BookingRepository;
import com.example.fitmatch.repository.ReviewRepository;
import com.example.fitmatch.repository.ServiceRepository;
import com.example.fitmatch.repository.TrainerProfileRepository;
import com.example.fitmatch.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ClientUiController {

    private final UserRepository userRepo;
    private final TrainerProfileRepository trainerRepo;
    private final BookingRepository bookingRepo;
    private final ServiceRepository serviceRepo;
    private final ReviewRepository reviewRepo;

    public ClientUiController(UserRepository userRepo, TrainerProfileRepository trainerRepo, BookingRepository bookingRepo, ServiceRepository serviceRepo, ReviewRepository reviewRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.bookingRepo = bookingRepo;
        this.serviceRepo = serviceRepo;
        this.reviewRepo = reviewRepo;
    }

    //Create Account
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRole(User.Role.CLIENT);
        userRepo.save(user);
        return "redirect:/login";
    }

    //Login 
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            jakarta.servlet.http.HttpSession session,
            Model model) {

        User user = userRepo.findByEmail(email).orElse(null);

        if (user != null && user.getPasswordHash().equals(password)) {

            // store user in session
            session.setAttribute("loggedInUser", user);

            TrainerProfile trainerProfile = trainerRepo.findByUserId(user.getId()).orElse(null);

            if (trainerProfile != null) {
                return "redirect:/provider/dashboard/" + trainerProfile.getId();
            }
            return "redirect:/trainers";
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    //Browse Trainers
   @GetMapping("/trainers")
public String trainers(
        @RequestParam(required = false) String location,
        Model model,
        HttpSession session) {

    List<TrainerProfile> trainers = trainerRepo.findByIsActiveTrue();

    if (location != null && !location.isBlank()) {
        trainers = trainers.stream()
                .filter(t -> t.getLocation() != null &&
                        t.getLocation().toLowerCase().contains(location.toLowerCase()))
                .toList();
    }

    model.addAttribute("trainers", trainers);
    model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

    return "trainers";
}

    @GetMapping("/trainer/{id}")
    public String trainerDetails(@PathVariable Long id, Model model, HttpSession session) {

        TrainerProfile trainer = trainerRepo.findById(id).orElse(null);

        model.addAttribute("trainer", trainer);
        model.addAttribute("services", serviceRepo.findByTrainerId(id));

        model.addAttribute("reviews", reviewRepo.findByTrainerId(id));

        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "trainer-details";
    }

    @PostMapping("/book")
public String book(
        @RequestParam Long serviceId,
        @RequestParam String date,
        @RequestParam String time,
        HttpSession session) {

    User client = (User) session.getAttribute("loggedInUser");

    if (client == null) {
        return "redirect:/login";
    }

    Service service = serviceRepo.findById(serviceId)
            .orElseThrow(() -> new RuntimeException("Service not found"));

    TrainerProfile trainer = service.getTrainer(); 

    Booking booking = new Booking();
    booking.setClient(client);
    booking.setService(service);
    booking.setTrainer(trainer);
    booking.setDate(LocalDate.parse(date));
    booking.setTime(LocalTime.parse(time));
    booking.setStatus(Booking.Status.PENDING);

    bookingRepo.save(booking);

    return "redirect:/profile";
}


@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/login";
}

@GetMapping("/become-trainer")
public String becomeTrainerPage(HttpSession session, Model model) {

    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) return "redirect:/login";

    TrainerProfile existing =
            trainerRepo.findByUserId(user.getId()).orElse(null);

    if (existing != null) {
        return "redirect:/provider/dashboard/" + existing.getId();
    }

    model.addAttribute("loggedInUser", user);
    model.addAttribute("trainerProfile", new TrainerProfile());

    return "become-trainer";
}

@PostMapping("/become-trainer")
public String becomeTrainer(@ModelAttribute TrainerProfile profile,
                            HttpSession session) {

    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) return "redirect:/login";

    // safety check (prevents duplicates)
    TrainerProfile existing =
            trainerRepo.findByUserId(user.getId()).orElse(null);

    if (existing != null) {
        return "redirect:/provider/dashboard/" + existing.getId();
    }

    user.setRole(User.Role.TRAINER);
    userRepo.save(user);

    profile.setUser(user);
    profile.setIsActive(true);
    profile.setAvgRating(0.0);

    TrainerProfile saved = trainerRepo.save(profile);

    return "redirect:/provider/dashboard/" + saved.getId();
}

@GetMapping("/profile")
public String profileRedirect(HttpSession session, Model model) {

    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) return "redirect:/login";

    model.addAttribute("loggedInUser", user);

    model.addAttribute("bookings", bookingRepo.findByClientId(user.getId()));

    model.addAttribute("reviews", reviewRepo.findByClientId(user.getId()));

    if (user.getRole() == User.Role.TRAINER) {
        TrainerProfile profile =
            trainerRepo.findByUserId(user.getId()).orElse(null);

        if (profile != null) {
            return "redirect:/provider/profile/" + profile.getId();
        }
    }

    return "userProfile"; 
}

@GetMapping("/review/{bookingId}")
public String reviewForm(@PathVariable Long bookingId,
                         HttpSession session,
                         Model model) {

    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) return "redirect:/login";

    Booking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

 
    if (!booking.getClient().getId().equals(user.getId())) {
        return "redirect:/userBookings";
    }

 
    if (reviewRepo.findByBookingId(bookingId).isPresent()) {
        return "redirect:/profile";
    }

    model.addAttribute("booking", booking);
    model.addAttribute("loggedInUser", user);

    return "review-form";
}

@PostMapping("/review/{bookingId}")
public String submitReview(@PathVariable Long bookingId,
                           @RequestParam int rating,
                           @RequestParam String text,
                           HttpSession session) {

    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) return "redirect:/login";

    Booking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

    Review review = new Review();
    review.setBooking(booking);
    review.setClient(user);
    review.setTrainer(booking.getTrainer());
    review.setRating(rating);
    review.setText(text);

    reviewRepo.save(review);

    return "redirect:/profile";
}
}
