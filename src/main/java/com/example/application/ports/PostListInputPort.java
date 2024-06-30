package com.example.application.ports;

import java.util.List;
import java.lang.IllegalArgumentException;
import org.springframework.dao.DataAccessException;
import com.example.application.dtos.PostDetailsDTO;

/**
 * Input contract for the PostListUseCase, receiving parameters from the primary adapter and passing them to the use case.
 * This interface defines the method to fetch posts based on various filtering criteria.
 */
public interface PostListInputPort {
    List<PostDetailsDTO> fetchPosts(String context, Integer page, Integer perPage, String search, String after, String modifiedAfter, List<Integer> author, List<Integer> authorExclude, String before, String modifiedBefore, List<Integer> exclude, List<Integer> include, Integer offset, String order, String orderBy, List<String> slug, List<String> status, List<Integer> categories, List<Integer> categoriesExclude, List<Integer> tags, List<Integer> tagsExclude, Boolean sticky) throws DataAccessException, IllegalArgumentException;
}