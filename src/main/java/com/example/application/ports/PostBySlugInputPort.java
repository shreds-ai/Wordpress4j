package com.example.application.ports;

import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.exceptions.PostNotFoundException;

public interface PostBySlugInputPort {
    PostDetailsDTO fetchPostBySlug(String slug) throws PostNotFoundException;
}