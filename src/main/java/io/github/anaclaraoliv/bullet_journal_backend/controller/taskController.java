package io.github.anaclaraoliv.bullet_journal_backend.controller;

import io.github.anaclaraoliv.bullet_journal_backend.dto.request.TaskPositionUpdateRequest;
import io.github.anaclaraoliv.bullet_journal_backend.entity.taskEntity;
import io.github.anaclaraoliv.bullet_journal_backend.service.taskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class taskController {

    private final taskService service;

    @GetMapping
    public List<taskEntity> getAllTasks() {
        return service.findAll();
    }

    @GetMapping("/date/{date}")
    public List<taskEntity> getTasksByDate(@PathVariable String date) {
        return service.findAllByDate(date);
    }

    @GetMapping("/task/{id}")
    public taskEntity getTasksById(@PathVariable String id) {
        return service.findTaskById(id);
    }

    @PostMapping()
    public ResponseEntity<taskEntity> createTask(@RequestBody taskEntity task) {
        taskEntity savedTask = service.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PatchMapping("/task/updateTask")
    public ResponseEntity<taskEntity> updateTasksPositions(@RequestBody taskEntity taskUpdate) {
        taskEntity updated = service.save(taskUpdate);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/task/updateStatus/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable String id) {
        return service.updateStatus(id)
                .map(task -> ResponseEntity.noContent().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/updateOrder")
    public ResponseEntity<Void> updateOrder(@RequestBody List<TaskPositionUpdateRequest> requests) {
        List<String> ids = requests.stream().map(TaskPositionUpdateRequest::id).toList();
        List<Integer> positions = requests.stream().map(TaskPositionUpdateRequest::position).toList();

        service.updateOrderByPositions(ids, positions);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (service.deleteTaskById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}