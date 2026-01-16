package io.github.anaclaraoliv.bullet_journal_backend.repository;

import io.github.anaclaraoliv.bullet_journal_backend.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface taskRepository extends JpaRepository<TaskEntity, String> {

    List<TaskEntity> findAllByDate(String date);

    // Os métodos abaixo já são herdados do JpaRepository
    // findAll()
    // deleteTaskById
    // existsById;
    // updateTaskById
}