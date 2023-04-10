package io.github.toms74209200.service.impl;

import io.github.toms74209200.domain.Email;
import io.github.toms74209200.domain.User;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.repository.UsersRepository;
import io.github.toms74209200.service.AuthService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;

    @Override
    public Optional<String> postAuth(Email email, String password) throws RepositoryException {
        User user = usersRepository.findByEmail(email).orElse(null);
        if (user == null) {
            log.info("User not found: {}", email);
            return Optional.empty();
        }

        if (user.password().equals(password)) {
            log.info("Login success. email: {}, password: {}", email, password);
            return Optional.of(""); // TODO: generate token
        }
        log.info("Invalid password. email: {}, password: {}", email, password);
        return Optional.empty();
    }
}
