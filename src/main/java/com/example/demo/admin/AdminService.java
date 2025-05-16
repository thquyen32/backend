/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.LogHistory.AdminLog;
import com.example.demo.LogHistory.AdminLogRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private AdminLogRepo adminLogRepo;

    public Optional<Admin> login(String username, String password) {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            Admin a= admin.get();
            a.setLastlogin(LocalDateTime.now());
            adminRepo.save(a);
            adminLogRepo.save(new AdminLog(username, LocalDateTime.now(), null));
            return Optional.of(a);
        }
        return Optional.empty();
    }
    public boolean register(Map<String, String> data) {
        String username = data.get("username");
        if(adminRepo.findByUsername(username).isPresent()) {
            return false; // Tên đăng nhập đã tồn tại
        }
        Admin newAdmin = new Admin();
        newAdmin.setUsername(username);
        newAdmin.setPassword(data.get("password"));
        newAdmin.setFullName(data.get("fullName"));
        newAdmin.setEmail(data.get("email"));
        newAdmin.setPhoneNumber(data.get("phoneNumber"));
        adminRepo.save(newAdmin);
        return true;
    }
    public boolean ChangePassword(String username,String oldPass, String newPass) {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isPresent()) {
            Admin a= admin.get();
            if(a.getPassword().equals(oldPass)) {
                a.setPassword(newPass);
                adminRepo.save(a);
                return true;
            }
        }
        return false;
    }


public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

public void updateLogoutTime(String username) {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isPresent()) {
            Admin a= admin.get();
            a.setLastlogout(LocalDateTime.now());
            adminRepo.save(a);
            AdminLog latestLog = adminLogRepo.findTopByUsernameOrderByLoginTimeDesc(username);
            if (latestLog != null && latestLog.getLogoutTime() == null) {
                latestLog.setLogoutTime(LocalDateTime.now());
                adminLogRepo.save(latestLog);
            }
        }
    }
public Admin updateInfo(String username, String fullName, String email, String phoneNumber) {
        Optional<Admin> admin = adminRepo.findByUsername(username);
        if (admin.isPresent()) {
            Admin a= admin.get();
            a.setFullName(fullName);
            a.setEmail(email);
            a.setPhoneNumber(phoneNumber);
            return adminRepo.save(a);
        }
        return null;
    }
}