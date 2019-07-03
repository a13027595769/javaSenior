package com.springboottask.service;

import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    //@Scheduled(cron = "*/1 * * * * MON-SAT")
    public void hello(){
        System.out.println("hello....");
    }
}
