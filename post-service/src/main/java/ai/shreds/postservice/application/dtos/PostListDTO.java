package ai.shreds.postservice.application.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Data transfer object for the list of posts, used to transfer data between the primary adapter and the application layer.
 */
@Data
public class PostListDTO {

    private List<PostDetailsDTO> posts = new ArrayList<>();

    public List<PostDetailsDTO> getPostList() {
        return posts;
    }

    public void setPostList(List<PostDetailsDTO> posts) {
        this.posts = new ArrayList<>(posts);
    }

    public static Builder builder() {
        return new Builder();
    }

    public void addPosts(List<PostDetailsDTO> posts) {
        if (posts != null) {
            this.posts.addAll(posts);
        }
    }

    public void clearPosts() {
        this.posts.clear();
    }

    public static class Builder {
        private List<PostDetailsDTO> posts = new ArrayList<>();

        public Builder addPost(PostDetailsDTO post) {
            if (post != null) {
                this.posts.add(post);
            }
            return this;
        }

        public Builder posts(List<PostDetailsDTO> posts) {
            if (posts != null) {
                this.posts = new ArrayList<>(posts);
            }
            return this;
        }

        public PostListDTO build() {
            PostListDTO postListDTO = new PostListDTO();
            postListDTO.setPostList(this.posts);
            return postListDTO;
        }
    }
}