package com.task.management.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.task.management.auth.entity.User;
import com.task.management.infra.exception.UnauthorizedException;
import com.task.management.infra.exception.InvalidTokenException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.secretKey());
            return  JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(jwtProperties.expiration()))
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            throw new UnauthorizedException("Token unauthorized");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.secretKey());

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();


        } catch (TokenExpiredException ex) {
            throw new UnauthorizedException("Token expired");

        }catch(JWTVerificationException ex){
            throw new InvalidTokenException("Token is invalid");
        }
    }
}