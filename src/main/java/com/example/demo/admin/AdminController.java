/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.admin;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.LogHistory.AdminLogRepo;


@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminLogRepo adminLogRepo;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> data, HttpSession session) {
        String username = data.get("username");
        String password = data.get("password");

        Optional<Admin> admin = adminService.login(username, password);
        if (admin.isPresent()) {
            session.setAttribute("admin", admin.get());
            return Map.of("success", true);
        } else {
            return Map.of("success", false, "error", "Sai tài khoản hoặc mật khẩu!");
        }
    }

    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin!=null) { 
            adminService.updateLogoutTime(admin.getUsername());
        }
       
        session.invalidate();
        return Map.of("success", true);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> data){
        boolean registered = adminService.register(data);
        if(registered){
            return Map.of("success", true);
        }else{
            return Map.of("success", false, "error", "Tài khoản đã tồn tại");
        }
    }

    @PostMapping("/changePassword")
    public Map<String, Object> changePassword(@RequestBody Map<String, String> data, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        String oldPass= data.get("oldPass");
        String newPass= data.get("newPass");
        boolean changed = adminService.ChangePassword(admin.getUsername(), oldPass, newPass);
        return changed 
        ? Map.of("success", true)
        : Map.of("success", false, "error", "Mật khẩu cũ không đúng!");
    }

    @GetMapping("/getAllAdmins")
    public List<Map<String, Object>> getAllAdmins() {
        return adminService.getAllAdmins().stream()
        .map(admin -> {
            Map<String, Object> adminMap = new HashMap<>();
            adminMap.put("id", admin.getId());
            adminMap.put("username", admin.getUsername());
            adminMap.put("fullName", admin.getFullName());
            adminMap.put("email", admin.getEmail());
            adminMap.put("phoneNumber", admin.getPhoneNumber());
            adminMap.put("lastLogin", admin.getLastlogin());
            adminMap.put("lastLogout", admin.getLastlogout());
            return adminMap;
        }).collect(Collectors.toList());
    }

    @GetMapping("/getAdminInfo")
    public Map<String,Object> getInfo(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {
                Map<String, Object> adminInfo = new HashMap<>();
                adminInfo.put("username", admin.getUsername());
                adminInfo.put("fullName", admin.getFullName());
                adminInfo.put("email", admin.getEmail());
                adminInfo.put("phoneNumber", admin.getPhoneNumber());
                return Map.of("success", true, "adminInfo", adminInfo);
            } else {
                return Map.of("success", false, "error", "Chưa đăng nhập!");
        }
    }

    @PutMapping("/updateInfo")
    public Map<String, Object> updateInfo(@RequestBody Map<String, String> data, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            String fullName = data.get("fullName");
            String email = data.get("email");
            String phoneNumber = data.get("phoneNumber");

            Admin updatedAdmin = adminService.updateInfo(admin.getUsername(), fullName, email, phoneNumber);
            if (updatedAdmin != null) {
                return Map.of("success", true);
            } else {
                return Map.of("success", false, "error", "Cập nhật thông tin thất bại!");
            }
        } else {
            return Map.of("success", false, "error", "Chưa đăng nhập!");
        }
    }
    @GetMapping("/getAdminLogs")
    public List<Map<String,Object>> getAdminLogs() {
        return adminLogRepo.findAll().stream().map(log -> {
            Map<String, Object> logMap = new HashMap<>();
            logMap.put("username", log.getUsername());
            logMap.put("loginTime", log.getLoginTime());
            logMap.put("logoutTime", log.getLogoutTime());
            return logMap;
        }).collect(Collectors.toList());
    }
}