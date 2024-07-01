package com.example.application.use_cases;

import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.ports.PostListParameters;

import java.util.List;

public interface PostListUseCase {
    List<PostDetailsDTO> fetchPosts(PostListParameters params);

}