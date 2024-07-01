package ai.shreds.wordpress4j.commentRetrieval.infrastructure.repositories;

import ai.shreds.wordpress4j.commentRetrieval.domain.entities.CommentEntity;
import ai.shreds.wordpress4j.commentRetrieval.domain.ports.CommentRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(CommentRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CommentEntity> findAllComments() {
        if (entityManager == null) {
            logger.error("EntityManager is not initialized.");
            return new ArrayList<>();
        }
        logger.info("Starting to fetch all comments from the database");
        try {
            List<CommentEntity> comments = entityManager.createQuery(
                            "SELECT DISTINCT c FROM CommentEntity c " +
                            "LEFT JOIN FETCH c.comment_meta m ", CommentEntity.class)
                    .getResultList();
            logger.info("Fetched {} comments successfully.", comments.size());
            return comments;
        } catch (Exception e) {
            logger.error("Error fetching comments: ", e);
            throw e;
        }
    }

    @Override
    public Optional<CommentEntity> findCommentById(Long id) {
        if (entityManager == null) {
            logger.error("EntityManager is not initialized.");
            return Optional.empty();
        }
        logger.info("Starting to fetch comment by ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("Invalid ID: {}, ID must be non-null and positive", id);
            return Optional.empty();
        }
        try {
            Optional<CommentEntity> comment = entityManager.createQuery(
                            "SELECT DISTINCT c FROM CommentEntity c " +
                            "LEFT JOIN FETCH c.comment_meta m " +
                            "WHERE c.id = :id", CommentEntity.class)
                    .setParameter("id", id)
                    .getResultList()
                    .stream()
                    .findFirst();

            logger.info("Fetch operation completed.");
            return comment;
        } catch (Exception e) {
            logger.error("Error fetching comment by ID: ", e);
            throw e;
        }
    }
}