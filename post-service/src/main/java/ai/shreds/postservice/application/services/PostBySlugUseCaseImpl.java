package ai.shreds.postservice.application.services;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.application.mappers.PostDetailsMapper;
import ai.shreds.postservice.application.use_cases.PostBySlugUseCase;
import ai.shreds.postservice.domain.entities.PostEntity;
import ai.shreds.postservice.domain.exceptions.PostNotFoundException;
import ai.shreds.postservice.domain.ports.PostRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostBySlugUseCaseImpl implements PostBySlugUseCase {
    private static final Logger logger = LoggerFactory.getLogger(PostBySlugUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;

    @Value("${application.base-url}")
    private String baseUrl;


    public PostBySlugUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    public PostDetailsDTO fetchPostBySlug(String slug) throws PostNotFoundException {
        Optional<PostEntity> postEntity = postRepositoryPort.findBySlug(slug);
        if (!postEntity.isPresent()) {
            throw new PostNotFoundException("Post not found with slug '" + slug + "'");
        }
        return PostDetailsMapper.toDTO(postEntity.get(), baseUrl);
    }
}