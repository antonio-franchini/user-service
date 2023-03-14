package com.userservice.app.security;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AuthorizationFilterTest {

    @Test
    void testAuthorizationFilter() {
        AuthorizationFilter authorizationFilter = new AuthorizationFilter(createAuthenticationManager());
        assertNotNull(authorizationFilter);

    }

    private AuthenticationManager createAuthenticationManager() {
        AuthenticationManager am = mock(AuthenticationManager.class);
        given(am.authenticate(any(Authentication.class)))
                .willAnswer((Answer<Authentication>) (invocation) -> (Authentication) invocation.getArguments()[0]);
        return am;
    }
}
