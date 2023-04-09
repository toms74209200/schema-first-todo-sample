package io.github.toms74209200.service.impl;

import io.github.toms74209200.domain.Email;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.repository.UsersRepository;
import io.github.toms74209200.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public long postUsers(Email email, String password) throws RepositoryException {
        return usersRepository.save(email, password);
    }
}
