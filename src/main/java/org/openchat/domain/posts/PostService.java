package org.openchat.domain.posts;

public class PostService {
  public Post createPost(String userId, String newPost) {
    return new Post("text from PostService");
  }
}
