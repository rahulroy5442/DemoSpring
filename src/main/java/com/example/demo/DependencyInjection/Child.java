package com.example.demo.DependencyInjection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Child extends Parent {
    @Override
    public String DoIt(){
        return "Hello world Rahul";
    };
}
