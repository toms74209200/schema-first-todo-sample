package io.github.toms74209200.bean;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmBean {

    @Bean
    public Algorithm algorithm(RSAKeyProvider rsaKeyProvider) {
        return Algorithm.RSA256(rsaKeyProvider);
    }

    @Bean
    private RSAKeyProvider rsaKeyProvider(KeyPairGenerator keyPairGenerator) {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new RSAKeyProvider() {

            private final RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            private final RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            @Override
            public RSAPublicKey getPublicKeyById(String s) {
                return publicKey;
            }

            @Override
            public RSAPrivateKey getPrivateKey() {
                return privateKey;
            }

            @Override
            public String getPrivateKeyId() {
                return null;
            }
        };
    }

    @Bean
    private KeyPairGenerator keyPairGenerator() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
