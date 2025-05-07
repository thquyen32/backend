package com.example.demo.hotel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class room_service {
    private final roomRepo repotority;

    @Autowired
    public room_service(roomRepo repotority) {
        this.repotority = repotority;
    }

    public boolean find(room rooms) {
        Optional<room> roomOptional = repotority.findRoomByRoomName(rooms.getRoomName());
        if (roomOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public long delete(String roomName) {
        LocalDateTime now = LocalDateTime.now();
        Optional<room> roomOptional = repotority.findRoomByRoomName(roomName);
        room saveRoom = roomOptional.get();
        LocalDateTime past = saveRoom.getDate();
        long time = ChronoUnit.HOURS.between(past, now);
        saveRoom.setState(false);
        saveRoom.setSdt(null);
        saveRoom.setTenKh(null);
        saveRoom.setCCCD(null);
        repotority.save(saveRoom);
        System.out.println(time);
        return tinhtien(time, roomName);
    }

    public void update_gio(String roomName) {
        Optional<room> roomOptional = repotority.findRoomByRoomName(roomName);
        if (roomOptional.isPresent()) {
            room saveRoom = roomOptional.get();
            saveRoom.setDate();
            repotority.save(saveRoom);
        }
    }

    public void datphong(String roomName, room rooms) {
        Optional<room> roomOptional = repotority.findRoomByRoomName(roomName);
        System.out.println(roomName);
        if (roomOptional.isPresent()) {
            room saveRoom = roomOptional.get();
            saveRoom.setTenKh(rooms.getTenKh());
            saveRoom.setSdt(rooms.getSdt());
            saveRoom.setCCCD(rooms.getCCCD());
            saveRoom.setState(true);
            repotority.save(saveRoom);
        } else
            System.out.println("Loi update");
    }

    public List<room> getRoom() {
        return repotority.findAll();

    }

    public long tinhtien(long time, String roomName) {
        long bill = 0;

        switch (roomName) {
            case "Phong 1":
                bill = time * 250;
                break;
            case "Phong 2":
                bill = time * 250;
                break;
            case "Phong 3":
                bill = time * 250;
                break;
            case "Phong 4":
                bill = time * 250;
                break;
            case "Phong 5":
                bill = time * 250;
                break;
            case "Phong 6":
                bill = time * 250;
                break;
            default:
                break;
        }
        return bill;
    }

}
