package ai.shreds.wordpress4j.mediaRetrieval.infrastructure.repositories;

import ai.shreds.wordpress4j.mediaRetrieval.domain.entities.MediaItemEntity;
import ai.shreds.wordpress4j.mediaRetrieval.domain.ports.MediaRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
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
            return entityManager.createQuery("SELECT m FROM MediaItemEntity m " +
                                             "LEFT JOIN FETCH m.mediaMetaEntities t " +
                                             "WHERE m.postType = :post_type", MediaItemEntity.class)
                    .setParameter("post_type", "attachment")
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Failed to fetch media items", e) {
            };
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MediaItemEntity> findMediaItemById(Long id) throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT m FROM MediaItemEntity m WHERE m.postType = :post_type", MediaItemEntity.class)
                    .setParameter("post_type", "attachment")
                    .getResultList()
                    .stream().findAny();
        } catch (Exception e) {
            throw new DataAccessException("Failed to fetch media item with ID: " + id, e) {
            };
        }
    }
}