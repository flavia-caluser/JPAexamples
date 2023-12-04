package com.springapps.jpaexamples.twitterapp;

import com.springapps.jpaexamples.orderapp.Order;
import com.springapps.jpaexamples.orderapp.OrderRepository;
import com.springapps.jpaexamples.orderapp.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TwitterRunner implements CommandLineRunner {
    UserService userService;
    @Autowired
    public TwitterRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Costel");
        userService.saveUser(user);

        Tweet tweet = new Tweet("bitcoin is up");
        userService.addTweetToUser1(user.getId(),tweet);

       // userService.deleteAllTweetsByUser2(1L); // nu functioneaza
        userService.deleteAllTweetsByUser(1L);



    }
}
