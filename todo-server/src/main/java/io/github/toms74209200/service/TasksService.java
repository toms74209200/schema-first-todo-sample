package io.github.toms74209200.service;

import io.github.toms74209200.model.Task;
import io.github.toms74209200.repository.RepositoryException;
import java.util.List;

public interface TasksService {
    void deleteTasks(long id) throws RepositoryException;

    long postTasks(long userId, Task task) throws RepositoryException;

    void putTasks(long id, long userId, Task task) throws RepositoryException;

    List<Task> getTasks(long userId) throws RepositoryException;
}
