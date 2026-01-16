package io.github.anaclaraoliv.bullet_journal_backend.controller;

import io.github.anaclaraoliv.bullet_journal_backend.entity.TaskEntity;
import io.github.anaclaraoliv.bullet_journal_backend.service.taskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // :5173
public class taskController {

    private final taskService service;

    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return service.findAll();
    }

    @GetMapping("/date/{date}")
    public List<TaskEntity> getTasksByDate(@PathVariable String date) {
        return service.findAllByDate(date);
    }

    @GetMapping("/task/{id}")
    public TaskEntity getTasksById(@PathVariable String id) {
        return service.findTaskById(id);
    }

    @PostMapping()
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        TaskEntity savedTask = service.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

//@PutMapping("/editTask/{id}")
//public ResponseEntity<TaskEntity> updateTask(@PathVariable String id, @RequestBody TaskEntity taskDetails) {
//    return service.updateTaskById(id, taskDetails)
//            .map(ResponseEntity::ok)
//            .orElse(ResponseEntity.notFound().build());
//}

    @PatchMapping("/task/updateStatus/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable String id) {
        return service.updateStatus(id)
                .map(task -> ResponseEntity.noContent().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (service.deleteTaskById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}