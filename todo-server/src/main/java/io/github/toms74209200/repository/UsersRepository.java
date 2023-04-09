package io.github.toms74209200.repository;

import io.github.toms74209200.domain.Email;
import io.github.toms74209200.domain.User;
import java.util.Optional;

public interface UsersRepository {

    /**
     * Save a user to the database.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The number of id of the user.
     */
    long save(Email email, String password) throws RepositoryException;

    Optional<User> findById(long id) throws RepositoryException;

    Optional<User> findByEmail(Email email) throws RepositoryException;
}
