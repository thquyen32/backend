package com.example.demo.hotel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class room_service {
    private final roomRepo repotority;
    @Autowired
    private JavaMailSender EmailSender;

    @Autowired
    public room_service(roomRepo repotority) {
        this.repotority = repotority;
    }

    public boolean check(String roomName, room rooms) {
        Optional<room> roomOptional = repotority.findRoomByRoomName(roomName);
        if (roomOptional.isPresent()) {
            room saveRoom = roomOptional.get();
            if (saveRoom.getState() == true) {
                System.out.println("co phong r");
                return false;
            } else {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo("gaosilver831@gmail.com");
                message.setSubject("Dat Phong");
                message.setText("Khách: " + rooms.getTenKh() +
                        "\nCCCD: " + rooms.getCCCD() +
                        "\nSĐT: " + rooms.getSdt() +
                        "\nPhòng: " + roomName);
                EmailSender.send(message);
                System.out.println("Gui mail");
            }
        }
        return true;
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
        saveRoom.setDate(null);
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
                bill = time * 80000;
                break;
            case "Phong 2":
                bill = time * 100000;
                break;
            case "Phong 3":
                bill = time * 200000;
                break;
            case "Phong 4":
                bill = time * 300000;
                break;
            case "Phong 5":
                bill = time * 400000;
                break;
            case "Phong 6":
                bill = time * 500000;
                break;
            default:
                break;
        }
        return bill;
    }

}