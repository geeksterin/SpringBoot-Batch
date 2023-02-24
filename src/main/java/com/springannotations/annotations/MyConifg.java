package com.springannotations.annotations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

// this is used to tell spring that there are few configs that we've defined
@Configuration
@ComponentScan({"componentScanTest"})
public class MyConifg {

    //To create a new bean/object for a given class and keep it with spring and give it back
    //when we do autowired
    @Bean
    public Date getDate() {
        System.out.println("new object of date class created");
        return new Date();
    }

    @Bean("user1")
    public User getUser1() {
        System.out.println("new object of user class created");
        return new User("akshay");
    }

    @Bean("user2")
    public User getUser2() {
        System.out.println("new object of user class created");
        return new User("Ankit");
    }


    @Bean
    @Lazy
    public Student getStudent() {
        System.out.println("new object created for student class");
        return new Student();
    }





}
