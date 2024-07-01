package ai.shreds.wordpress4j.categoryRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.OptionEntity;
import ai.shreds.wordpress4j.categoryRetrieval.domain.ports.OptionRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

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
