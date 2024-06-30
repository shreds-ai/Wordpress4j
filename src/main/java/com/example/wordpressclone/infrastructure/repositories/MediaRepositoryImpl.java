package com.example.wordpressclone.infrastructure.repositories;

import com.example.wordpressclone.domain.entities.MediaItemEntity;
import com.example.wordpressclone.domain.ports.MediaRepositoryPort;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the MediaRepositoryPort for interacting with the WordPress database.
 */
@Repository
public class MediaRepositoryImpl implements MediaRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<MediaItemEntity> findAllMediaItems() {
        try {
            return entityManager.createQuery("SELECT m FROM MediaItemEntity m", MediaItemEntity.class).getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to fetch media items", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MediaItemEntity> findMediaItemById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(MediaItemEntity.class, id));
        } catch (PersistenceException e) {
            throw new RuntimeException("Failed to fetch media item with ID: " + id, e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching media item with ID: " + id, e);
        }
    }
}