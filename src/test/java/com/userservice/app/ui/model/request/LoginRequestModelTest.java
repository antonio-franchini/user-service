package com.userservice.app.ui.model.request;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
class LoginRequestModelTest {
    @Test
    void settersAndGetters() {
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setEmail("test@test.com");
        loginRequestModel.setPassword("123abc");
        assertEquals(loginRequestModel.getEmail(), "test@test.com");
        assertEquals(loginRequestModel.getPassword(), "123abc");
    }

}
