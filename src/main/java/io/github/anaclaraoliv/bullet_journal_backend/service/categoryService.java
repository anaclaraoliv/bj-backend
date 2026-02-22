package io.github.anaclaraoliv.bullet_journal_backend.service;

import io.github.anaclaraoliv.bullet_journal_backend.entity.categoryEntity;
import io.github.anaclaraoliv.bullet_journal_backend.entity.taskEntity;
import io.github.anaclaraoliv.bullet_journal_backend.repository.categoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class categoryService {

    private final categoryRepository repository;

    public List<categoryEntity> findAll() {
        return repository.findAll();
    }

    }
