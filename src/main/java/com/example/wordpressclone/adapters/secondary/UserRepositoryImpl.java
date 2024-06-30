package com.example.wordpressclone.adapters.secondary;

import com.example.wordpressclone.domain.entities.UserEntity;
import com.example.wordpressclone.domain.ports.UserRepositoryPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository, UserRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAllUsers() throws PersistenceException, DataIntegrityViolationException {
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserById(Long id) throws PersistenceException, DataIntegrityViolationException {
        try {
            return Optional.ofNullable(entityManager.find(UserEntity.class, id));
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Override
    public boolean existsById(Long id) {
        try {
            return entityManager.createQuery("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.id = :id", Boolean.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (PersistenceException e) {
            throw e;
        }
    }
}