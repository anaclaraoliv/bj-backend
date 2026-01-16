package io.github.anaclaraoliv.bullet_journal_backend.service;

import io.github.anaclaraoliv.bullet_journal_backend.entity.TaskEntity;
import io.github.anaclaraoliv.bullet_journal_backend.repository.taskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class taskService {

    private final taskRepository repository;

    public List<TaskEntity> findAll() {
        return repository.findAll();
    }

    public List<TaskEntity> findAllByDate(String date) {
        return repository.findAllByDate(date);
    }

    public TaskEntity findTaskById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskEntity save(TaskEntity task) {
        return repository.save(task);
    }

    public Optional<TaskEntity> updateTaskById(String id, TaskEntity taskDetails) {
        return repository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.isStatus());
            task.setDate(taskDetails.getDate());
            task.setCategory(taskDetails.getCategory());
            return repository.save(task);
        });
    }

    public Optional<TaskEntity> updateStatus(String id) {
        return repository.findById(id).map(task -> {
            task.setStatus(!task.isStatus());
            return repository.save(task);
        });
    }

    public boolean deleteTaskById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}