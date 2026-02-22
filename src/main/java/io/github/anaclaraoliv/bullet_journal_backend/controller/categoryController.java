package io.github.anaclaraoliv.bullet_journal_backend.controller;

import io.github.anaclaraoliv.bullet_journal_backend.entity.categoryEntity;
import io.github.anaclaraoliv.bullet_journal_backend.service.categoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // :5173
public class categoryController {

    private final categoryService service;

    @GetMapping
    public List<categoryEntity> getAllTasks() {
        return service.findAll();
    }
}
