package ai.shreds.postservice.application.services;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.application.mappers.PostDetailsMapper;
import ai.shreds.postservice.application.use_cases.PostListUseCase;
import ai.shreds.postservice.domain.ports.PostListParameters;
import ai.shreds.postservice.domain.ports.PostRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostListUseCaseImpl implements PostListUseCase {
    private final PostRepositoryPort postRepositoryPort;

    @Value("${application.base-url}")
    private String baseUrl;


    public PostListUseCaseImpl(PostRepositoryPort postRepositoryPort) {
        this.postRepositoryPort = postRepositoryPort;
    }

    @Override
    public List<PostDetailsDTO> fetchPosts(PostListParameters params) {
        return postRepositoryPort.findAll(params).stream()
                .map(postEntity -> PostDetailsMapper.toDTO(postEntity, baseUrl))
                .collect(Collectors.toList());
    }
}