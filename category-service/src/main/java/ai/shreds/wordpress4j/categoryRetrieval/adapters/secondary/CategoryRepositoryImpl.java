package ai.shreds.wordpress4j.categoryRetrieval.adapters.secondary;

import ai.shreds.wordpress4j.categoryRetrieval.domain.entities.CategoryEntity;
import ai.shreds.wordpress4j.categoryRetrieval.domain.ports.CategoryRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryPort {

    private static final Logger logger = Logger.getLogger(CategoryRepositoryImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @Cacheable("categories")
    public List<CategoryEntity> findAllCategories() {
        logger.info("Fetching all categories from the database.");
        return entityManager.createQuery(
                        "SELECT DISTINCT c FROM CategoryEntity c " +
                        "LEFT JOIN FETCH c.termTaxonomy t " +
                        "LEFT JOIN FETCH c.termMeta m " +
                        "WHERE t.taxonomy = 'category'", CategoryEntity.class)
                .getResultList();
    }

    @Override
    @Transactional
    @Cacheable("categoryById")
    public Optional<CategoryEntity> findCategoryById(Long id) {
        logger.info("Fetching category by ID: " + id);
        return entityManager.createQuery(
                        "SELECT DISTINCT c FROM CategoryEntity c " +
                        "LEFT JOIN FETCH c.termTaxonomy t " +
                        "LEFT JOIN FETCH c.termMeta m " +
                        "WHERE t.taxonomy = 'category' AND c.id = :id", CategoryEntity.class)
                .setParameter("id", id)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    @Transactional
    @Cacheable("categoryByName")
    public Optional<CategoryEntity> findCategoryByName(String name) {
        logger.info("Fetching category by name: " + name);
        List<CategoryEntity> results = entityManager.createQuery(
                        "SELECT DISTINCT c FROM CategoryEntity c " +
                        "LEFT JOIN FETCH c.termTaxonomy t " +
                        "LEFT JOIN FETCH c.termMeta m " +
                        "WHERE t.taxonomy = 'category' AND c.name = :name", CategoryEntity.class)
                .setParameter("name", name)
                .getResultList();
        return results.stream().findFirst();
    }
}