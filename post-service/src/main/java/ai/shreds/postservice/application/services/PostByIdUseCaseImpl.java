package ai.shreds.postservice.application.services;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.application.mappers.PostDetailsMapper;
import ai.shreds.postservice.application.use_cases.PostByIdUseCase;
import ai.shreds.postservice.domain.entities.PostEntity;
import ai.shreds.postservice.domain.exceptions.PostNotFoundException;
import ai.shreds.postservice.domain.ports.PostRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class PostByIdUseCaseImpl implements PostByIdUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PostByIdUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;

    @Value("${application.base-url}")
    private String baseUrl;


    public PostByIdUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "posts", key = "#postId")
    public PostDetailsDTO getPostById(Long postId) throws PostNotFoundException {
        if (postId == null || postId < 1) {
            throw new IllegalArgumentException("Invalid post ID");
        }
        try {
            Optional<PostEntity> postEntity = postRepositoryPort.findById(postId);
            logger.debug("Fetching post with ID: " + postId);
            return postEntity.map(pe -> PostDetailsMapper.toDTO(postEntity.get(), baseUrl)).orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));} catch (Exception e) {
            logger.error("Error retrieving post with ID: " + postId, e);
            throw new PostNotFoundException("Error retrieving post", e);
        }
    }


}