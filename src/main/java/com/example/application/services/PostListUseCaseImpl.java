package com.example.application.services;

import com.example.application.ports.PostListInputPort;
import com.example.application.ports.PostListOutputPort;
import com.example.application.dtos.PostListDTO;
import com.example.domain.ports.PostRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostListUseCaseImpl implements PostListInputPort {

    private static final Logger logger = LoggerFactory.getLogger(PostListUseCaseImpl.class);
    private final PostRepositoryPort postRepositoryPort;
    private final PostListOutputPort postListOutputPort;

    public PostListUseCaseImpl(PostRepositoryPort postRepositoryPort, PostListOutputPort postListOutputPort) {
        this.postRepositoryPort = postRepositoryport;
        this.postListOutputPort = postListOutputPort;
    }

    @Override
    @Transactional(readonly = true)
    @Cacheable("posts")
    @PreAuthorize("hasRole('ADMIN')")
    public PostListDTO retrievePostList(int page, int size, String sortBy) {
        logger.debug("Fetching posts with page: {} size: {} sortBy: {}", page, size, sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        List<PostListDTO.PostDetailsDTO> posts = postRepositoryPort.findAll(pageable).stream()
                .map(postListOutputPort::convertToDTO)
                .collect(Collectors.toList());
        logger.debug("Posts retrieved successfully.");
        return PostListDTO.builder().addPosts(posts).build();
    }
}