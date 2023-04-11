package io.github.toms74209200.service.impl;

import io.github.toms74209200.model.Task;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.repository.TasksRepository;
import io.github.toms74209200.service.TasksService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TasksServiceImpl implements TasksService {

    TasksRepository tasksRepository;

    @Override
    public void deleteTasks(long id) throws RepositoryException {
        tasksRepository.delete(id);
    }

    @Override
    public long postTasks(long userId, Task task) throws RepositoryException {
        io.github.toms74209200.domain.Task newTask =
                io.github.toms74209200.domain.Task.of(userId, task);
        return tasksRepository.save(newTask);
    }

    @Override
    public void putTasks(long id, long userId, Task task) throws RepositoryException {
        io.github.toms74209200.domain.Task newTask =
                io.github.toms74209200.domain.Task.of(userId, task);
        tasksRepository.update(id, newTask);
    }

    @Override
    public List<Task> getTasks(long userId) throws RepositoryException {
        return tasksRepository.findByUserId(userId).entrySet().stream()
                .map(e -> e.getValue().to(e.getKey()))
                .toList();
    }
}
