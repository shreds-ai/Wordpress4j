package com.example.adapters.primary;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org..springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.application.dtos.PostDetailsDTO;
import com.example.application.use_cases.PostByIdUseCase;
import com.example.application.use_cases.PostBySlugUseCase;
import com.example.application.use_cases.PostListUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PostController {

    private final PostListUseCase postListUseCase;
    private final PostByIdUseCase postByIdUseCase;
    private final PostBySlugUseCase postBySlugUseCase;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    public PostController(PostListUseCase postListUseCase, PostByIdUseCase postByIdUseCase, PostBySlugUseCase postBySlugUseCase) {
        this.postListUseCase = postListUseCase;
        this.postByIdUseCase = postByIdUseCase;
        this.postBySlugUseCase = postBySlugUseCase;
    }

    @GetMapping("/wp-json/wp/v2/posts")
    @PreAuthorize("hasAuthority('ROLE_USER')")
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

    @GetMapping("/wp-json/wp/v2/posts/slug/{slug}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<PostDetailsDTO> getPostBySlug(@PathVariable @Valid String slug) {
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleExceptions(Exception e) {
        logger.error("Global exception handler caught: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
    }
}