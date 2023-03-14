package com.userservice.app.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SecurityConstantsTest {

    @Test
    void getTokenSecret() {
        String actual = SecurityConstants.getTokenSecret();
        assertEquals("jf9i4jgu83nfl0jfu57ejf7", actual);
    }
}
