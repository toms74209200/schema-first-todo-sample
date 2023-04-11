package io.github.toms74209200.repository.impl;

import io.github.toms74209200.domain.Task;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.repository.TasksRepository;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class TasksRepositoryImpl implements TasksRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public long save(Task task) throws RepositoryException {
        tasks.add(task);
        return tasks.size() - 1;
    }

    @Override
    public Optional<Task> findById(long id) throws RepositoryException {
        if (id < tasks.size()) {
            return Optional.of(tasks.get((int) id));
        }
        return Optional.empty();
    }

    @Override
    public Map<Long, Task> findByUserId(long userId) throws RepositoryException {
        Map<Long, Task> map = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.userId() == userId) {
                map.put((long) i, task);
            }
        }
        return map;
    }

    @Override
    public void update(long id, Task task) throws RepositoryException {
        tasks.set((int) id, task);
    }

    @Override
    public void delete(long id) throws RepositoryException {
        tasks.remove((int) id);
    }
}
