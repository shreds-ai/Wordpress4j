package com.wordpressclone.tags.adapters.secondary;

import com.wordpressclone.tags.domain.entities.TagEntity;
import com.wordpressclone.tags.domain.ports.TagRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(TagRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<TagEntity> findAllTags() {
        logger.info("Fetching all tags");
        try {
            return entityManager.createQuery("SELECT t FROM TagEntity t", TagEntity.class).getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            logger.error("Database error occurred while fetching all tags", e);
            throw new RuntimeException("Error accessing database to find all tags.", e);
        }
    }

    @Override
    @Transactional
    public Optional<TagEntity> findTagById(Long id) {
        logger.info("Fetching tag by ID: {}", id);
        try {
            return Optional.ofNullable(entityManager.find(TagEntity.class, id));
        } catch (NoResultException e) {
            logger.error("Tag not found with ID: {}", id, e);
            throw new RuntimeException("Error accessing database to find tag by ID.", e);
        }
    }

    @Override
    @Transactional
    public Optional<TagEntity> findTagByName(String name) {
        logger.info("Fetching tag by name: {}", name);
        try {
            return entityManager.createQuery("SELECT t FROM TagEntity t WHERE t.name = :name", TagEntity.class)
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .findFirst();
        } catch (NoResultException e) {
            logger.error("Tag not found with name: {}", name, e);
            throw new RuntimeException("Error accessing database to find tag by name.", e);
        }
    }
}