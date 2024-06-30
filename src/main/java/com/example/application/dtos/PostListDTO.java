package com.example.application.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;

@Data
public class PostListDTO {

    private List<PostDetailsDTO> posts = new ArrayList<>();

    public List<PostDetailsDTO> getPostList() {
        return posts;
    }

    private void setPostList(List<PostDetailsDTO> posts) {
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
                List<PostDetailsDTO> newPosts = new ArrayList<>(this.posts);
                newPosts.add(post);
                return new Builder().posts(newPosts);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostListDTO that = (PostListDTO) o;
        return Objects.equals(posts, that.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posts);
    }

    @Override
    public String toString() {
        return "PostListDTO{" +
                "posts=" + posts +
                '}';
    }
}