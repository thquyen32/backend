package com.example.demo.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {

    // Đường dẫn hiển thị trang đăng nhập
    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }
    
    // Đường dẫn hiển thị trang chủ (chỉ hiện nếu admin đã đăng nhập)
    @GetMapping("/home")
    public String homePage(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";  // Nếu không có admin trong session, chuyển đến login
        }
        return "home";  // Nếu đã đăng nhập, hiển thị trang chủ 
    }
    @GetMapping("/room_home")
    public String roomPage(HttpSession session) {
      return "room_home";  // Nếu đã đăng nhập, hiển thị trang chủ 
    }
    @GetMapping("/bookingform")
    public String bookingPage(HttpSession session) {
      return "bookingform"; 
    }
}
