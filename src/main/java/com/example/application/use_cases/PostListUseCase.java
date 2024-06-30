package com.example.application.use_cases;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.example.application.dtos.PostDetailsDTO;
import com.example.domain.services.PostService;

@Aspect
@Component
public class AuditAspect {
    // Aspect implementation for auditing
}

public interface PostListUseCase {
    @Cacheable("postsByCriteria")
    @Async
    default List<PostDetailsDTO> fetchPostsGeneric(PostFilterCriteria criteria) {
        // Generic method implementation
    }

    @Cacheable("postsByDate")
    List<PostDetailsDTO> fetchPostsByDate(String after, String before);

    @Cacheable("postsByAuthor")
    List<PostDetailsDTO> fetchPostsByAuthor(List<Integer> author, List<Integer> authorExclude);

    @Cacheable("postsByStatus")
    List<PostDetailsDTO> fetchPostsByStatus(List<String> status);
}