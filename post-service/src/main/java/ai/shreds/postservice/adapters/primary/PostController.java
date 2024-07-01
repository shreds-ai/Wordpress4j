package ai.shreds.postservice.adapters.primary;

import java.util.List;
import javax.validation.Valid;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import ai.shreds.postservice.application.use_cases.PostByIdUseCase;
import ai.shreds.postservice.application.use_cases.PostBySlugUseCase;
import ai.shreds.postservice.application.use_cases.PostListUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ai.shreds.postservice.domain.ports.PostListParameters;

@RestController
@RequestMapping("/wp-json/wp/v2")
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

    @GetMapping("/posts")
    public ResponseEntity<List<PostDetailsDTO>> getAllPosts(PostListParameters params) {
        List<PostDetailsDTO> posts = postListUseCase.fetchPosts(params);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDetailsDTO> getPostById(@PathVariable @Valid Long id) {
        logger.info("Fetching post by ID: {}", id);
        try {
            PostDetailsDTO post = postByIdUseCase.getPostById(id);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            logger.error("Exception encountered while fetching post by ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/posts/slug/{slug}")
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