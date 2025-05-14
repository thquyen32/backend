package com.example.demo.LogHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLogRepo extends JpaRepository<AdminLog, Long>  {
    List<AdminLog> findByUsername(String username);
    AdminLog findTopByUsernameOrderByLoginTimeDesc(String username);
}