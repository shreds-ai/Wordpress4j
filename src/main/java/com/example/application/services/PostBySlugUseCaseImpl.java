package com.example.application.services;

import com.example.application.use_cases.PostBySlugUseCase;
import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.ports.PostRepositoryPort;
import com.example.domain.exceptions.PostNotFoundException;
import com.example.domain.entities.PostEntity;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.annotation.Timed;

/**
 * Service implementation for retrieving a post by its slug with enhanced security and detailed logging.
 */
@Service
@Validated
public class PostBySlugUseCaseImpl implements PostBySlugUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PostBySlugUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;

    public PostBySlugUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    @Timed(value = "post.fetchBySlug", description = "Measure time taken to fetch post by slug")
    public PostDetailsDTO execute(@Valid String slug) throws PostNotFoundException {
        logger.debug("Attempting to fetch post by slug: {}", slug);
        try {
            Optional<PostDetailsDTO> optionalPost = postRepositoryPort.findBySlug(slug)
                .map(post -> new PostDetailsDTO(
                    post.getId(),
                    post.getDate(),
                    post.getDateGmt(),
                    post.getGuid(),
                    post.getRendered(),
                    post.getModified(),
                    post.getModifiedGmt(),
                    post.getSlug(),
                    post.getStatus(),
                    post.getType(),
                    post.getLink(),
                    post.getTitle(),
                    post.getContent(),
                    post.getExcerpt(),
                    post.getAuthor(),
                    post.getFeaturedMedia(),
                    post.getCommentStatus(),
                    post.getPingStatus(),
                    post.isSticky(),
                    post.getTemplate(),
                    post.getFormat(),
                    post.getMeta(),
                    post.getCategories(),
                    post.getTags(),
                    post.getLinks(),
                    post.getVersion()
                ));
            return optionalPost.orElseThrow(() -> new PostNotFoundException("Post with slug '" + slug + "' not found"));
        } catch (QueryTimeoutException e) {
            logger.error("Query timeout occurred while fetching post by slug: {}", slug, e);
            throw new PostNotFoundException("Timeout during database access for post by slug", e);
        } catch (DataAccessException e) {
            logger.error("Database access error while fetching post by slug: {}", slug, e);
            throw new PostNotFoundException("Database access issue on fetching post by slug", e);
        }
    }
}