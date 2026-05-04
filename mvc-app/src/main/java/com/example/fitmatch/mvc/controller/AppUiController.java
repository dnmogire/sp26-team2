package com.example.fitmatch.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.fitmatch.repository.TrainerProfileRepository;


@Controller
public class AppUiController {

    private final TrainerProfileRepository trainerRepo;
    
    public AppUiController(TrainerProfileRepository trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredTrainers", trainerRepo.findAll().stream().limit(2).toList());
        return "index";
    }

     @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("featuredTrainers", trainerRepo.findAll().stream().limit(2).toList());
        return "index";
    } 
}
