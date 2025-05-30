package com.example.demo.admin;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {
   
    @Bean(name="adminCLR")
   public CommandLineRunner adminCLR(AdminRepo adminRepo) {
        return args -> {
            if (adminRepo.count() == 0) {
                Admin ad1= new Admin(null,"domixi","123"); 
                Admin ad2= new Admin(null,"alado","5678"); 

                adminRepo.saveAll(List.of(ad1,ad2));
        }
    };
    }
}
