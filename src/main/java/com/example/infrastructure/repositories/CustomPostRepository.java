package com.example.infrastructure.repositories;

import com.example.domain.entities.PostEntity;
import com.example.domain.ports.PostListParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomPostRepository {

    @Transactional(readOnly = true)
    List<PostEntity> findAll(PostListParameters params);

    @Transactional(readOnly = true)
    Optional<PostEntity> findById(Long id);

    @Transactional(readOnly = true)
    Optional<PostEntity> findBySlug(String slug);
}