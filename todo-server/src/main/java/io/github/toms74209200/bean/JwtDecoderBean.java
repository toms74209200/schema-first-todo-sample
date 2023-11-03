package io.github.toms74209200.bean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.toms74209200.config.ServerConfig;
import java.time.Instant;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoderBean {

    private final JWTVerifier verifier;

    public JwtDecoderBean(Algorithm algorithm, ServerConfig serverConfig) {
        this.verifier = JWT.require(algorithm).withIssuer(serverConfig.domain()).build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return token -> {
            DecodedJWT decodedJWT = verifier.verify(token);
            return Jwt.withTokenValue(token)
                    .audience(decodedJWT.getAudience())
                    .expiresAt(Instant.ofEpochMilli(decodedJWT.getExpiresAt().getTime()))
                    .jti(decodedJWT.getId())
                    .issuedAt(Instant.ofEpochMilli(decodedJWT.getIssuedAt().getTime()))
                    .issuer(decodedJWT.getIssuer())
                    .header("alg", decodedJWT.getAlgorithm())
                    .build();
        };
    }
}
