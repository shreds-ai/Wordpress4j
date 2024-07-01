package ai.shreds.postservice.application.use_cases;

import ai.shreds.postservice.application.dtos.PostDetailsDTO;
import ai.shreds.postservice.domain.ports.PostListParameters;

import java.util.List;

public interface PostListUseCase {
    List<PostDetailsDTO> fetchPosts(PostListParameters params);

}