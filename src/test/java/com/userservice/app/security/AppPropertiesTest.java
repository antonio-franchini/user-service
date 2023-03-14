package com.userservice.app.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppPropertiesTest {

    @Autowired
    AppProperties appProperties;

    @Mock
    private Environment env;

    @Test
    void getTokenSecret() {
        when(env.getProperty(anyString())).thenReturn("jf9i4jgu83nfl0jfu57ejf7");
        String actual = appProperties.getTokenSecret();
        assertEquals("jf9i4jgu83nfl0jfu57ejf7", actual);
    }
}