package com.example.application.services;

import com.example.application.dtos.PostDetailsDTO;
import com.example.application.mappers.PostDetailsMapper;
import com.example.application.use_cases.PostListUseCase;
import com.example.domain.ports.PostListParameters;
import com.example.domain.ports.PostRepositoryPort;
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