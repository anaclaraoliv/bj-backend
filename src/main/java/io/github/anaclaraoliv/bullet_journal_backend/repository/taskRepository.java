package io.github.anaclaraoliv.bullet_journal_backend.repository;

import io.github.anaclaraoliv.bullet_journal_backend.entity.taskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface taskRepository extends JpaRepository<taskEntity, String> {

    List<taskEntity> findAllByDate(String date);
}