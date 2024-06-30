package com.example.domain.ports;

import java.util.List;
import java.util.Optional;
import com.example.domain.entities.PostEntity;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;

/**
 * Defines the contract for retrieving and persisting posts, used by the application services
 * to interact with the data layer without being coupled to any specific database technology.
 */
public interface PostRepositoryPort {

    Optional<PostEntity> findById(Long id);

    Optional<PostEntity> findBySlug(String slug);

    List<PostEntity> findAll(PostListParameters listParameters);

    PostEntity save(PostEntity post);

    void deleteById(Long id);

    boolean handleDatabaseException(SQLException e, DataAccessException dae);
}