package com.example.application.ports;

import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.exceptions.DataRetrievalException;

public interface PostBySlugInputPort {
    PostDetailsDTO fetchPostBySlug(String slug) throws DataRetrievalException;
}