package main.java.com.example.fitmatch.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppUiController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
