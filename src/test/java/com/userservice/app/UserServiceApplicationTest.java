package com.userservice.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceApplicationTest {

    @Test
    void bCryptPasswordEncoder() {
        UserServiceApplication userServiceApplication = new UserServiceApplication();
        assertNotNull(userServiceApplication.bCryptPasswordEncoder());
    }

    @Test
    void springApplicationContext() {
        UserServiceApplication userServiceApplication = new UserServiceApplication();
        assertNotNull(userServiceApplication.springApplicationContext());
    }

    @Test
    void getAppProperties() {
        UserServiceApplication userServiceApplication = new UserServiceApplication();
        assertNotNull(userServiceApplication.getAppProperties());
    }
}
