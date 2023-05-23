package com.example.demo.DependencyInjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Child2{


    @Bean("qhild2")
    public String DoIt(@Qualifier("pchild") Parent p){
        return p.DoIt();
    };
}
