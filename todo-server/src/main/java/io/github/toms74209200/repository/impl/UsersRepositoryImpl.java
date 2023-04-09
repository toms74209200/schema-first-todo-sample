package io.github.toms74209200.repository.impl;

import io.github.toms74209200.domain.Email;
import io.github.toms74209200.domain.User;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.repository.UsersRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UsersRepositoryImpl implements UsersRepository {

    Map<Long, User> users = new HashMap<>();

    @Override
    public long save(Email email, String password) throws RepositoryException {
        boolean exists = users.values().stream().anyMatch(user -> user.email().equals(email));
        if (exists) {
            throw new RepositoryException("User already exists.");
        }
        long id = users.size() + 1;
        users.put(id, new User(email, password));
        return id;
    }

    @Override
    public Optional<User> findById(long id) throws RepositoryException {
        if (users.containsKey(id)) {
            return Optional.of(users.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(Email email) throws RepositoryException {
        return users.values().stream().findFirst();
    }
}
