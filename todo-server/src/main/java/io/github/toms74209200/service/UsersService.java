package io.github.toms74209200.service;

import io.github.toms74209200.domain.Email;
import io.github.toms74209200.repository.RepositoryException;

public interface UsersService {
    long postUsers(Email email, String password) throws RepositoryException;
}
