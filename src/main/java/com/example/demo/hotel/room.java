package com.example.demo.hotel;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roomName;
    private boolean state;
    private String tenKh;
    private String sdt;
    private String cccd;
    private LocalDateTime date;

    public room() {

    }

    public room(String roomName) {
        this.roomName = roomName;
        this.state = false;
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean getState() {
        return state;
    }

    public String getTenKh() {
        return tenKh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getCCCD() {
        return cccd;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setCCCD(String cccd) {
        this.cccd = cccd;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setDate() {
        this.date = LocalDateTime.now();
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
