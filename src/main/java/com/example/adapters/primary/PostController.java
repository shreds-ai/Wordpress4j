package com.example.adapters.primary;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.application.dtos.PostDetailsDTO;
import com.example.application.use_cases.PostByIdUseCase;
import com.example.application.use_cases.PostBySlugUseCase;
import com.example.application.use_cases.PostListUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RateLimit;

@RestController
@ControllerAdvice
public class PostController {

    private final PostListUseCase postListUseCase;
    private final PostByIdUseCase postByIdUseCase;
    private final PostBySlugUseCase postBySlugUseCase;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    public PostController(PostListUseCase postListUseCase, PostByIdUseCase postByIdUseCase, PostBySlugUseCase postBySlugUseCase) {
        this.postListUseCase = postListUsease;
        this.postByIdUseCase = postByIdUseCase;
        this.postBySlugUseCase = postBySlugUseCase;
    }

    @GetMapping("/wp-json/wp/v2/posts")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RateLimit(1000, 3600)
    @Cacheable("postsCache")
    public ResponseEntity<List<PostDetailsDTO>> getAllPosts(@Valid @RequestParam Map<String, String> params) {
        logger.info("Fetching all posts with validated params: {}", params);
        List<PostDetailsDTO> posts = postListUseCase.fetchPosts(params);
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/wp-json/wp/v2/posts/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RateLimit(500, 3600)
    public ResponseEntity<PostDetailsDTO> getPostById(@PathVariable @Valid Long id) {
        logger.info("Fetching post by ID: {}", id);
        try {
            PostDetailsDTO post = postByIdUseCase.execute(id);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            logger.error("Exception encountered while fetching post by ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/wp-json/wp/v2/posts/slug")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RateLimit(500, 3600)
    public ResponseEntity<PostDetailsDTO> getPostBySlug(@Valid @RequestParam List<String> slug) {
        logger.info("Fetching post by validated slug: {}", slug);
        try {
            PostDetailsDTO post = postBySlugUseCase.fetchPostBySlug(slug);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            logger.error("Exception encountered while fetching post by slug: {}", slug, e);
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        logger.error("Global exception handler caught: {}", e.getMessage());
        return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
    }
}