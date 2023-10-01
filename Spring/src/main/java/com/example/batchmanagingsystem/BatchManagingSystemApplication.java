package com.example.batchmanagingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BatchManagingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchManagingSystemApplication.class, args);
    }

}
