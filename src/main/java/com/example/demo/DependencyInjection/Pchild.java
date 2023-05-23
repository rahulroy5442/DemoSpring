package com.example.demo.DependencyInjection;

import org.springframework.stereotype.Component;

@Component
public class Pchild extends Parent{
    @Override
    public String DoIt(){
        return "Hello world Rahul form Pchild";
    };
}
