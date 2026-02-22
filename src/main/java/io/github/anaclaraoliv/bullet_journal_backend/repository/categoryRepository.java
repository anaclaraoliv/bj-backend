package io.github.anaclaraoliv.bullet_journal_backend.repository;

import io.github.anaclaraoliv.bullet_journal_backend.entity.categoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface categoryRepository extends JpaRepository<categoryEntity, String> {

//    List<categoryEntity> ;

}
