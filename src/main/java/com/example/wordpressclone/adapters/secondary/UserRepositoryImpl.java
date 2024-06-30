package com.example.wordpressclone.adapters.secondary;

import com.example.wordpressclone.domain.entities.UserEntity;
import com.example.wordpressclone.domain.ports.UserRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.IllegalArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAllUsers() {
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
        } catch (NoResultException e) {
            logger.error("No users found", e);
            return List.of();
        } catch (QueryTimeoutException e) {
            logger.error("Query timed out", e);
            throw new RuntimeException("Database query timed out", e);
        } catch (PersistenceException e) {
            logger.error("Persistence error encountered", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(UserEntity.class, id));
        } catch (QueryTimeoutException e) {
            logger.error("Query to find user by ID timed out", e);
            throw new RuntimeException("Database query timed out", e);
        } catch (PersistenceException e) {
            logger.error("Persistence error encountered while fetching user by ID", e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument passed", e);
            throw e;
        }
    }

    @Override
    public boolean existsById(Long id) {
        try {
            return entityManager.createQuery("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.id = :id", Boolean.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (QueryTimeoutException e) {
            logger.error("Existence check query timed out", e);
            throw new RuntimeException("Database query timed out", e);
        } catch (PersistenceException e) {
            logger.error("Persistence error encountered during existence check", e);
            throw e;
        }
    }
}