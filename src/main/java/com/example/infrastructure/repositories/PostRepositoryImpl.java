package com.example.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import com.example.domain.ports.PostRepositoryPort;
import com.example.domain.entities.PostEntity;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.infrastructure.repositories.CustomPostRepository;
import com.example.domain.exceptions.PostNotFoundException;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

@Repository
public class PostRepositoryImpl implements PostRepositoryPort {

    private final CustomPostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class);
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public PostRepositoryImpl(CustomPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostEntity> findAll(Pageable pageable) {
        try {
            List<PostEntity> posts = postRepository.findAll(pageable).getContent();
            eventPublisher.publishEvent(new AuditApplicationEvent("retrieveAllPosts", this.getClass().getSimpleName(), "Success"));
            return posts;
        } catch (DataAccessException e) {
            logger.error("Failed to retrieve posts", e);
            throw new PostNotFoundException("Failed to retrieve posts", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostEntity> findById(Long id) {
        try {
            Optional<PostEntity> post = postRepository.findById(id);
            eventPublisher.publishEvent(new AuditApplicationEvent("retrievePostById", this.getClass().getSimpleName(), "Success"));
            return post;
        } catch (DataAccessException e) {
            logger.error("Failed to find post by ID", e);
            throw new PostNotFoundException("Failed to find post by ID", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostEntity> findBySlug(String slug) {
        try {
            Optional<PostEntity> post = postRepository.findBySlug(slug);
            eventPublisher.publishEvent(new AuditApplicationEvent("retrievePostBySlug", this.getClass().getSimpleName(), "Success"));
            return post;
        } catch (DataAccessException e) {
            logger.error("Failed to find post by slug", e);
            throw new PostNotFoundException("Failed to find post by slug", e);
        }
    }
}