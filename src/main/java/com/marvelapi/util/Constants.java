package com.marvelapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    @Autowired
    Environment environment;

    @Value("${security.key.public}")
    public static String PUBLIC_KEY;

    @Value("${security.key.public}")
    public void setPublicKey(String token) {
        PUBLIC_KEY = token;
    }

    @Value("${security.key.private}")
    public static String PRIVATE_KEY;

    @Value("${security.key.private}")
    public void setPublicPrivate(String token) {
        PRIVATE_KEY = token;
    }
}
