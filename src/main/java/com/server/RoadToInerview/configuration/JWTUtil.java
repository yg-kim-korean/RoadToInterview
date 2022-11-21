package com.server.RoadToInerview.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.server.RoadToInerview.domain.Users;
import org.springframework.beans.factory.annotation.Value;
import java.time.Instant;

public class JWTUtil {
    @Value("${data.secret-key}")
    private static String secret_key;
    private static final long AUTH_TIME = 20*60;
    public static String makeAuthToken(Users users){

        return JWT.create().withSubject(users.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond()+AUTH_TIME)
                .sign(Algorithm.HMAC256(secret_key));
    }
}
