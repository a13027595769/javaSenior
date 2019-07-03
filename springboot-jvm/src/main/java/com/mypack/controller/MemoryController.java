package com.mypack.controller;

import com.mypack.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MemoryController {
    private List<User> userList = new ArrayList<>();
    private List<Class<?>> classList = new ArrayList<>();
    @GetMapping("/heap")
    public String heap(){
        while (true){
            userList.add(new User());
        }
    }
    @GetMapping("/noheap")
    public String noheap(){
        while (true){

        }
    }
}
