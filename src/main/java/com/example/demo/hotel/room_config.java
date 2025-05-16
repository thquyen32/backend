package com.example.demo.hotel;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class room_config {
    @Bean
    CommandLineRunner commandLineRunner(roomRepo repotority) {
        return args -> {
            if (repotority.count() == 0) {
                room phong1 = new room("Phong 1");
                room phong2 = new room("Phong 2");
                room phong3 = new room("Phong 3");
                room phong4 = new room("Phong 4");
                room phong5 = new room("Phong 5");
                room phong6 = new room("Phong 6");
                repotority.saveAll(List.of(phong1, phong2, phong3, phong4, phong5, phong6));
            }
        };
    }

}
