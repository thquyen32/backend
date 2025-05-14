package com.example.demo.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/room")
public class room_controller {
    private final room_service room_service;

    @Autowired
    public room_controller(room_service room_service) {
        this.room_service = room_service;
    }

    @PutMapping(path = "/check/{roomName}")
    public String check(
            @PathVariable("roomName") String roomName,
            @RequestBody room rooms) {
        if (room_service.check(roomName, rooms) == true) {
            return "true";
        } else {
            return "false";
        }
    }

    @PutMapping(path = "/update/{roomName}")
    public void update_gio(
            @PathVariable("roomName") String roomName) {
        room_service.update_gio(roomName);
    }

    @PutMapping(path = "{roomName}")
    public void datphong(
            @PathVariable("roomName") String roomName,
            @RequestBody room rooms) {
        room_service.datphong(roomName, rooms);
    }

    @DeleteMapping(path = "/delete/{roomName}")
    public long delete(
            @PathVariable("roomName") String roomName) {
        return room_service.delete(roomName);
    }

    @GetMapping("/get")
    public List<room> get() {
        return room_service.getRoom();
    }
}