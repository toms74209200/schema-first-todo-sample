package io.github.toms74209200.repository;

import io.github.toms74209200.domain.Task;
import java.util.Map;
import java.util.Optional;

public interface TasksRepository {

    long save(Task task) throws RepositoryException;

    Optional<Task> findById(long id) throws RepositoryException;

    Map<Long, Task> findByUserId(long userId) throws RepositoryException;

    void update(long id, Task task) throws RepositoryException;

    void delete(long id) throws RepositoryException;
}
