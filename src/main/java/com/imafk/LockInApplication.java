package com.imafk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
*  LockIn is a data privacy vault service.
*  It allows users to store sensitive data in key value pairs.
*  Author: Rishav Kumar
 */

@SpringBootApplication
public class LockInApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockInApplication.class, args);
    }
}