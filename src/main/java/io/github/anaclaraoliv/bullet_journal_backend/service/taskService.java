package io.github.anaclaraoliv.bullet_journal_backend.service;

import io.github.anaclaraoliv.bullet_journal_backend.dto.request.TaskPositionUpdateRequest;
import io.github.anaclaraoliv.bullet_journal_backend.entity.taskEntity;
import io.github.anaclaraoliv.bullet_journal_backend.repository.taskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class taskService {

    private final taskRepository repository;

    public List<taskEntity> findAll() {
        return repository.findAll();
    }

    public List<taskEntity> findAllByDate(String date) {
        return repository.findAllByDate(date);
    }

    public taskEntity findTaskById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public taskEntity save(taskEntity task) {
        return repository.save(task);
    }

    @Transactional
    public List<taskEntity> updatePositionsByDate(String date, List<TaskPositionUpdateRequest> updates) {
        Map<String, Integer> positionById = updates.stream()
                .collect(Collectors.toMap(
                        TaskPositionUpdateRequest::id,
                        TaskPositionUpdateRequest::position,
                        (a, b) -> b
                ));

        List<taskEntity> tasks = repository.findAllByDate(date);

        tasks.forEach(task -> {
            Integer newPosition = positionById.get(task.getId());
            if (newPosition != null) {
                task.setPosition(newPosition);
            }
        });

        return repository.saveAll(tasks);
    }

    @Transactional
    public Optional<taskEntity> updateStatus(String id) {
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

    @Transactional
    public List<taskEntity> updateOrderByPositions(List<String> ids, List<Integer> positions) {
        List<taskEntity> tasks = repository.findAllById(ids);

        Map<String, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            positionMap.put(ids.get(i), positions.get(i));
        }

        tasks.forEach(task -> {
            Integer newPosition = positionMap.get(task.getId());
            if (newPosition != null) {
                task.setPosition(newPosition);
            }
        });

        return repository.saveAll(tasks);
    }

}