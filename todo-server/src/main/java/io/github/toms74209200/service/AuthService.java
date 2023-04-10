package io.github.toms74209200.service;

import io.github.toms74209200.domain.Email;
import io.github.toms74209200.repository.RepositoryException;
import java.util.Optional;

public interface AuthService {

    Optional<String> postAuth(Email email, String password) throws RepositoryException;
}
