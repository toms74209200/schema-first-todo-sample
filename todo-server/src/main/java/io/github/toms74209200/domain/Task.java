package io.github.toms74209200.domain;

import java.time.Instant;
import java.time.ZoneId;

public record Task(
        long userId, String name, String description, Instant deadline, Boolean completed) {

    public static Task of(long userId, io.github.toms74209200.model.Task task) {
        return new Task(
                userId,
                task.getName(),
                task.getDescription(),
                Instant.from(task.getDeadline()),
                task.getCompleted());
    }

    public io.github.toms74209200.model.Task to(long id) {
        return io.github.toms74209200.model.Task.builder()
                .id(id)
                .name(name)
                .description(description)
                .deadline(
                        deadline.atOffset(
                                ZoneId.systemDefault().getRules().getStandardOffset(null)))
                .completed(completed)
                .build();
    }
}
