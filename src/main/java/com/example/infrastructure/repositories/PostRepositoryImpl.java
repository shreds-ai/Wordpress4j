package com.example.infrastructure.repositories;

import com.example.domain.entities.*;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.ports.PostListParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements CustomPostRepository {

    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional(readOnly = true)
    public List<PostEntity> findAll(PostListParameters params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostEntity> cq = cb.createQuery(PostEntity.class);
        Root<PostEntity> postRoot = cq.from(PostEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getSearch() != null) {
            predicates.add(cb.like(postRoot.get("postTitle"), "%" + params.getSearch() + "%"));
        }
        if (params.getAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(postRoot.get("postDate"), params.getAfter()));
        }
        if (params.getModifiedAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(postRoot.get("postModified"), params.getModifiedAfter()));
        }
        if (params.getAuthor() != null && !params.getAuthor().isEmpty()) {
            predicates.add(postRoot.get("postAuthor").in(params.getAuthor()));
        }
        if (params.getAuthorExclude() != null && !params.getAuthorExclude().isEmpty()) {
            predicates.add(cb.not(postRoot.get("postAuthor").in(params.getAuthorExclude())));
        }
        if (params.getBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(postRoot.get("postDate"), params.getBefore()));
        }
        if (params.getModifiedBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(postRoot.get("postModified"), params.getModifiedBefore()));
        }
        if (params.getExclude() != null && !params.getExclude().isEmpty()) {
            predicates.add(cb.not(postRoot.get("id").in(params.getExclude())));
        }
        if (params.getInclude() != null && !params.getInclude().isEmpty()) {
            predicates.add(postRoot.get("id").in(params.getInclude()));
        }
        if (params.getSlug() != null && !params.getSlug().isEmpty()) {
            predicates.add(postRoot.get("slug").in(params.getSlug()));
        }
        if (params.getStatus() != null) {
            predicates.add(cb.equal(postRoot.get("postStatus"), params.getStatus().toString()));
        }
        if (params.getCategories() != null && !params.getCategories().isEmpty()) {
            Join<PostEntity, TermTaxonomyEntity> categoryJoin = postRoot.join("termTaxonomies", JoinType.LEFT);
            predicates.add(cb.and(
                    cb.equal(categoryJoin.get("taxonomy"), "category"),
                    categoryJoin.get("term").get("id").in(params.getCategories())
            ));
        }
        if (params.getCategoriesExclude() != null && !params.getCategoriesExclude().isEmpty()) {
            Join<PostEntity, TermTaxonomyEntity> categoryJoin = postRoot.join("termTaxonomies", JoinType.LEFT);
            predicates.add(cb.not(cb.and(
                    cb.equal(categoryJoin.get("taxonomy"), "category"),
                    categoryJoin.get("term").get("id").in(params.getCategoriesExclude())
            )));
        }
        if (params.getTags() != null && !params.getTags().isEmpty()) {
            Join<PostEntity, TermTaxonomyEntity> tagJoin = postRoot.join("termTaxonomies", JoinType.LEFT);
            predicates.add(cb.and(
                    cb.equal(tagJoin.get("taxonomy"), "post_tag"),
                    tagJoin.get("term").get("id").in(params.getTags())
            ));
        }
        if (params.getTagsExclude() != null && !params.getTagsExclude().isEmpty()) {
            Join<PostEntity, TermTaxonomyEntity> tagJoin = postRoot.join("termTaxonomies", JoinType.LEFT);
            predicates.add(cb.not(cb.and(
                    cb.equal(tagJoin.get("taxonomy"), "post_tag"),
                    tagJoin.get("term").get("id").in(params.getTagsExclude())
            )));
        }

        cq.where(predicates.toArray(new Predicate[0])).distinct(true);
        cq.orderBy(params.getOrder().equalsIgnoreCase("desc") ?
                cb.desc(postRoot.get(params.getOrderBy())) :
                cb.asc(postRoot.get(params.getOrderBy())));

        TypedQuery<PostEntity> query = entityManager.createQuery(cq);
        int page = params.getPage();
        int perPage = params.getPerPage();
        int offset = (page - 1) * perPage;

        query.setFirstResult(offset);
        query.setMaxResults(perPage);

        List<PostEntity> posts = query.getResultList();


        // Fetch categories and tags for each post
        for (PostEntity post : posts) {
            List<TermEntity> categories = fetchTermsByTaxonomy(post.getId(), "category");
            post.setCategories(categories);

            List<TermEntity> tags = fetchTermsByTaxonomy(post.getId(), "post_tag");
            post.setTags(tags);

            Long featuredMedia = fetchFeaturedMedia(post.getId());
            post.setFeaturedMedia(featuredMedia);
        }

        return posts;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PostEntity> findById(Long id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<PostEntity> cq = cb.createQuery(PostEntity.class);
            Root<PostEntity> postRoot = cq.from(PostEntity.class);

            // Fetch post by ID
            cq.select(postRoot).where(cb.equal(postRoot.get("id"), id));

            TypedQuery<PostEntity> query = entityManager.createQuery(cq);
            PostEntity post = query.getSingleResult();

            if (post != null) {
                // Fetch categories
                List<TermEntity> categories = fetchTermsByTaxonomy(post.getId(), "category");
                post.setCategories(categories);

                // Fetch tags
                List<TermEntity> tags = fetchTermsByTaxonomy(post.getId(), "post_tag");
                post.setTags(tags);

                // Fetch author information
                UserEntity author = post.getPostAuthor();
                if (author != null) {
                    post.setAuthorName(author.getDisplayName());
                }

                // Set other fields
                post.setGuidRendered(post.getGuid());
                post.setTitleRendered(post.getPostTitle());
                post.setContentRendered(post.getPostContent());
                post.setExcerptRendered(post.getPostExcerpt());


                // Fetch featured media
                Long featuredMedia = fetchFeaturedMedia(post.getId());
                post.setFeaturedMedia(featuredMedia);

            }

            return Optional.of(post);
        } catch (NoResultException e) {
            logger.error("Failed to find post by ID", e);
            return Optional.empty();
        } catch (DataAccessException e) {
            logger.error("Failed to find post by ID", e);
            throw new PostNotFoundException("Failed to find post by ID", e);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PostEntity> findBySlug(String slug) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<PostEntity> cq = cb.createQuery(PostEntity.class);
            Root<PostEntity> postRoot = cq.from(PostEntity.class);

            // Fetch post by slug
            cq.select(postRoot).where(cb.equal(postRoot.get("postName"), slug));

            TypedQuery<PostEntity> query = entityManager.createQuery(cq);
            PostEntity post = query.getSingleResult();

            if (post != null) {
                // Fetch categories
                List<TermEntity> categories = fetchTermsByTaxonomy(post.getId(), "category");
                post.setCategories(categories);

                // Fetch tags
                List<TermEntity> tags = fetchTermsByTaxonomy(post.getId(), "post_tag");
                post.setTags(tags);

                // Fetch author information
                UserEntity author = post.getPostAuthor();
                if (author != null) {
                    post.setAuthorName(author.getDisplayName());
                }

                // Set other fields
                post.setGuidRendered(post.getGuid());
                post.setTitleRendered(post.getPostTitle());
                post.setContentRendered(post.getPostContent());
                post.setExcerptRendered(post.getPostExcerpt());


                // Fetch featured media
                Long featuredMedia = fetchFeaturedMedia(post.getId());
                post.setFeaturedMedia(featuredMedia);

            }

            return Optional.of(post);
        } catch (NoResultException e) {
            logger.error("Failed to find post by slug", e);
            return Optional.empty();
        } catch (DataAccessException e) {
            logger.error("Failed to find post by slug", e);
            throw new PostNotFoundException("Failed to find post by slug", e);
        }
    }


    private List<TermEntity> fetchTermsByTaxonomy(Long postId, String taxonomy) {
        TypedQuery<TermEntity> query = entityManager.createQuery(
                "SELECT t FROM TermEntity t JOIN t.termTaxonomies tt JOIN tt.posts p WHERE p.id = :postId AND tt.taxonomy = :taxonomy", TermEntity.class);
        query.setParameter("postId", postId);
        query.setParameter("taxonomy", taxonomy);
        return query.getResultList();
    }

    private Long fetchFeaturedMedia(Long postId) {
        String sql = "SELECT pm.metaValue " +
                "FROM PostmetaEntity pm " +
                "WHERE pm.post.id = :postId " +
                "AND pm.metaKey = '_thumbnail_id'";

        TypedQuery<String> query = entityManager.createQuery(sql, String.class);
        query.setParameter("postId", postId);

        List<String> results = query.getResultList();
        return !results.isEmpty() ? Long.parseLong(results.get(0)) : 0L;
    }


}
