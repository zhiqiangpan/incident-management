package com.example.incidentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author panzhiqiang
 * @Description 事件应用启动类
 * @Date 2024/11/28
 */
@SpringBootApplication
@EnableCaching
public class IncidentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncidentManagementApplication.class, args);
    }
}
