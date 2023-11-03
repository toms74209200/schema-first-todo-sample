package io.github.toms74209200.controller;

import io.github.toms74209200.api.TasksApi;
import io.github.toms74209200.model.*;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.service.TasksService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TasksController implements TasksApi {

    private final NativeWebRequest request;
    private final TasksService tasksService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(request);
    }

    @Override
    public ResponseEntity<TaskId> deleteTasks(Long taskId) {
        try {
            tasksService.deleteTasks(taskId);
        } catch (RepositoryException e) {
            log.warn("Failed to delete task: {}", taskId, e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(TaskId.builder().id(taskId).build());
    }

    @Override
    public ResponseEntity<List<Task>> getTasks(Long userId) {
        return TasksApi.super.getTasks(userId);
    }

    @Override
    public ResponseEntity<PostTasks201Response> postTasks(PostTasksRequest postTasksRequest) {
        return TasksApi.super.postTasks(postTasksRequest);
    }

    @Override
    public ResponseEntity<Task> putTasks(Long taskId, PutTasksRequest putTasksRequest) {
        return TasksApi.super.putTasks(taskId, putTasksRequest);
    }
}
