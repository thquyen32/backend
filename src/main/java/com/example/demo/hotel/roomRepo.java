package com.example.demo.hotel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roomRepo extends JpaRepository<room, Integer> {

    Optional<room> findRoomByRoomName(String roomName);

}
