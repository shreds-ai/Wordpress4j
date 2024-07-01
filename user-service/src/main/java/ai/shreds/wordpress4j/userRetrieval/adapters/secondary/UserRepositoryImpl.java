package ai.shreds.wordpress4j.userRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.userRetrieval.domain.entities.UserEntity;
import ai.shreds.wordpress4j.userRetrieval.domain.entities.UserMetaEntity;
import ai.shreds.wordpress4j.userRetrieval.domain.ports.UserRepositoryPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryPort {

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
    @Transactional(readOnly = true)
    public List<UserMetaEntity> findUserMetadataByUserId(Long userId) {
        try {
            return entityManager.createQuery("SELECT um FROM UserMetaEntity um WHERE um.userId = :userId", UserMetaEntity.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (PersistenceException e) {
            throw e;
        }
    }
}