package ai.shreds.wordpress4j.tagRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.tagRetrieval.domain.entities.TagEntity;
import ai.shreds.wordpress4j.tagRetrieval.domain.ports.TagRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            return entityManager.createQuery(
                            "SELECT DISTINCT c FROM TagEntity c " +
                            "LEFT JOIN FETCH c.termTaxonomy t " +
                            "LEFT JOIN FETCH c.termMeta m " +
                            "WHERE t.taxonomy = 'post_tag'", TagEntity.class)
                    .getResultList();
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
            return entityManager.createQuery("SELECT c FROM TagEntity c LEFT JOIN FETCH c.termTaxonomy t LEFT JOIN FETCH c.termMeta m WHERE t.taxonomy = 'post_tag' AND c.id = :id", TagEntity.class)
                    .setParameter("id", id)
                    .getResultList()
                    .stream()
                    .findFirst();
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
            return entityManager.createQuery("SELECT c FROM TagEntity c LEFT JOIN FETCH c.termTaxonomy t LEFT JOIN FETCH c.termMeta m WHERE t.taxonomy = 'post_tag' AND c.name = :name", TagEntity.class)
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