package com.example.fitmatch.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.fitmatch.model.User;
import com.example.fitmatch.model.TrainerProfile;
import com.example.fitmatch.model.Booking;
import com.example.fitmatch.repository.BookingRepository;
import com.example.fitmatch.repository.ServiceRepository;
import com.example.fitmatch.repository.TrainerProfileRepository;
import com.example.fitmatch.repository.UserRepository;


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


    //CREATE ACCOUNT
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        user.setRole(User.Role.CLIENT);
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        
        User user = userRepo.findByEmail(email).orElse(null);

        if(user != null && user.getPasswordHash().equals(password)){
            return "redirect:/trainers";
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }


    //Browse Trainers
    @GetMapping("/trainers")
    public String trainers(Model model){

        List<TrainerProfile> trainers = trainerRepo.findByIsActiveTrue();
        model.addAttribute("trainers", trainers);

        return "trainers";
    }

    @GetMapping("/trainer/{id}")
    public String trainerDetails(@PathVariable Long id, Model model){

        TrainerProfile trainer = trainerRepo.findById(id).orElse(null);

        model.addAttribute("trainer", trainer);
        model.addAttribute("services", serviceRepo.findByTrainerId(id));
        return "trainer-details";
    }


    //BOOK trainer
    @PostMapping("/book")
    public String book(@RequestParam Long clientId, @RequestParam Long trainerId, @RequestParam Long serviceId, @RequestParam String date, @RequestParam String time){
        Booking booking = new Booking();

        booking.setClient(userRepo.findById(clientId).orElse(null));
        booking.setTrainer(trainerRepo.findById(trainerId).orElse(null));
        booking.setService(serviceRepo.findById(serviceId).orElse(null));

        booking.setDate(LocalDate.parse(date));
        booking.setTime(LocalTime.parse(time));
        booking.setStatus(Booking.Status.PENDING);

        bookingRepo.save(booking);

        return "redirect:/bookings";
    }
}