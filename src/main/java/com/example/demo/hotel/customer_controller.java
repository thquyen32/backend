package com.example.demo.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class customer_controller {

    @GetMapping
    public String index() {
        return "user";
    }

}
