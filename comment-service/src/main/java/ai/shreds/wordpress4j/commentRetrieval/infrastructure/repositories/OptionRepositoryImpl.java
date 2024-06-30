package ai.shreds.wordpress4j.commentRetrieval.infrastructure.repositories;

import ai.shreds.wordpress4j.commentRetrieval.domain.entities.OptionEntity;
import ai.shreds.wordpress4j.commentRetrieval.domain.ports.OptionRepositoryPort;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class OptionRepositoryImpl implements OptionRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OptionEntity findByOptionName(String optionName) {
        TypedQuery<OptionEntity> query = entityManager.createQuery(
                "SELECT o FROM OptionEntity o WHERE o.optionName = :optionName", OptionEntity.class);
        query.setParameter("optionName", optionName);
        return query.getSingleResult();
    }
}