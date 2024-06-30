package com.example.application.use_cases;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.value_objects.PostListFilter;

public interface PostListUseCase {
    @Cacheable(cacheNames = "#{T(com.example.application.config.CacheConfig).POSTS_BY_CRITERIA}")
    @Async
    List<PostDetailsDTO> fetchPostsGeneric(PostListFilter criteria);

    @Cacheable(cacheNames = "#{T(com.example.application.config.CacheConfig).POSTS_BY_DATE}")
    List<PostDetailsDTO> fetchPostsByDate(String after, String before);

    @Cacheable(cacheNames = "#{T(com.example.application.config.CacheConfig).POSTS_BY_AUTHOR}")
    List<PostDetailsDTO> fetchPostsByAuthor(List<Integer> author, List<Integer> authorExclude);

    @Cacheable(cacheNames = "#{T(com.example.application.config.CacheConfig).POSTS_BY_STATUS}")
    List<PostDetailsDTO> fetchPostsByStatus(List<String> status);

    List<PostDetailsDTO> fetchPosts(String context, Integer page, Integer perPage, String search, String after, String modifiedAfter, List<Integer> author, List<Integer> authorExclude, String before, String modifiedBefore, List<Integer> exclude, List<Integer> include, Integer offset, String order, String orderBy, List<String> slug, List<String> status, List<Integer> categories, List<Integer> categoriesExclude, List<Integer> tags, List<Integer> tagsExclude, Boolean sticky);
}