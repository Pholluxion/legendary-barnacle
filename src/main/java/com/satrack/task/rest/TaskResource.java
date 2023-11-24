package com.satrack.task.rest;

import com.satrack.task.model.TaskDTO;
import com.satrack.task.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskResource {

    private final TaskServiceImpl taskServiceImpl;

    public TaskResource(final TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(taskServiceImpl.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createTask(@RequestBody @Valid final TaskDTO taskDTO) {
        final Long createdId = taskServiceImpl.create(taskDTO);
        return  ResponseEntity.created(URI.create("/api/v1/tasks/" + createdId)).body(createdId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTask(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final TaskDTO taskDTO) {
        taskServiceImpl.update(id, taskDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") final Long id) {
        taskServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

}
