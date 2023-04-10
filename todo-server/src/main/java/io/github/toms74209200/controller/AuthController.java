package io.github.toms74209200.controller;

import io.github.toms74209200.api.AuthApi;
import io.github.toms74209200.domain.Email;
import io.github.toms74209200.model.Token;
import io.github.toms74209200.model.UserCredentials;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.service.AuthService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {

    private final NativeWebRequest request;
    private final AuthService authService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(request);
    }

    @Override
    public ResponseEntity<Token> postAuth(UserCredentials userCredentials) {
        log.info("{}", userCredentials);
        Email email;
        try {
            email = Email.of(userCredentials.getEmail());
        } catch (IllegalArgumentException e) {
            log.info("Invalid email: {}", userCredentials.getEmail());
            return ResponseEntity.badRequest().build();
        }

        if (userCredentials.getPassword() == null || userCredentials.getPassword().isEmpty()) {
            log.info("Invalid password: {}", userCredentials.getPassword());
            return ResponseEntity.badRequest().build();
        }

        String token;
        try {
            token = authService.postAuth(email, userCredentials.getPassword()).orElse(null);
        } catch (RepositoryException e) {
            log.error("Repository error", e);
            return ResponseEntity.internalServerError().build();
        }
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(Token.builder().token(token).build());
    }
}
