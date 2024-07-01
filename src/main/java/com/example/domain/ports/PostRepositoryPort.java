package com.example.domain.ports;

import com.example.domain.entities.PostEntity;
import com.example.domain.exceptions.PostNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Defines the contract for retrieving and persisting posts, used by the application services
 * to interact with the data layer without being coupled to any specific database technology.
 */

public interface PostRepositoryPort {

    Optional<PostEntity> findById(Long id) throws PostNotFoundException;

    Optional<PostEntity> findBySlug(String slug) throws PostNotFoundException;

    List<PostEntity> findAll(PostListParameters listParameters);

}