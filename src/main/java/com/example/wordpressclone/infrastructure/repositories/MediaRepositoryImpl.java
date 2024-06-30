package com.example.wordpressclone.infrastructure.repositories;

import com.example.wordpressclone.domain.entities.MediaItemEntity;
import com.example.wordpressclone.domain.ports.MediaRepositoryPort;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
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
    public List<MediaItemEntity> findAllMediaItems() throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT m FROM MediaItemEntity m", MediaItemEntity.class).getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Failed to fetch media items", e) {};
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MediaItemEntity> findMediaItemById(Long id) throws DataAccessException {
        try {
            return Optional.ofNullable(entityManager.find(MediaItemEntity.class, id));
        } catch (Exception e) {
            throw new DataAccessException("Failed to fetch media item with ID: " + id, e) {};
        }
    }
}