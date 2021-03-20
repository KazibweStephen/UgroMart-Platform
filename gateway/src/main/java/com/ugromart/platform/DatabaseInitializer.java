package com.ugromart.platform;

import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {

        User user =new User();
        user.setUsername("kstephen3@gmail.com");
        user.setPassword("12345");
        user.setCustomerEmail("kstephen3@gmail.com");
        user.setCustomerFullName("Stephen Kazibwe");
        user.setPasswordConfirm("12345");
        user.setCustomerPhoneNumber("0782832711");

        userService.save(user);
 }
}
