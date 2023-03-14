package com.userservice.app.ui.model.request;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
class UserLoginRequestModelTest {
    @Test
    void settersAndGetters() {
        UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel();
        userLoginRequestModel.setEmail("test@test.com");
        userLoginRequestModel.setPassword("123abc");
        assertEquals(userLoginRequestModel.getEmail(), "test@test.com");
        assertEquals(userLoginRequestModel.getPassword(), "123abc");
    }

}
