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
import com.example.fitmatch.model.Service;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.model.User;
import com.example.fitmatch.repository.BookingRepository;
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

    public ClientUiController(UserRepository userRepo, TrainerProfileRepository trainerRepo, BookingRepository bookingRepo, ServiceRepository serviceRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.bookingRepo = bookingRepo;
        this.serviceRepo = serviceRepo;
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

            return "redirect:/trainers";
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    //Browse Trainers
    @GetMapping("/trainers")
    public String trainers(Model model, HttpSession session) {

        List<TrainerProfile> trainers = trainerRepo.findByIsActiveTrue();
        model.addAttribute("trainers", trainers);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "trainers";
    }

    @GetMapping("/trainer/{id}")
    public String trainerDetails(@PathVariable Long id, Model model, HttpSession session) {

        TrainerProfile trainer = trainerRepo.findById(id).orElse(null);

        model.addAttribute("trainer", trainer);
        model.addAttribute("services", serviceRepo.findByTrainerId(id));

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

    return "redirect:/userBookings";
}

@GetMapping("/userBookings")
public String userBookings(Model model, HttpSession session) {

    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) {
        return "redirect:/login";
    }

    model.addAttribute("bookings",
        bookingRepo.findByClientId(user.getId())
    );

    return "Userbookings";
}

@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/login";
}
}
