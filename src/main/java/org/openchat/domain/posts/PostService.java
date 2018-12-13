package org.openchat.domain.posts;

import org.openchat.domain.users.IdGenerator;

public class PostService {

  private final PostRepository postRepository;
  private final Clock clock;
  private IdGenerator postIdGeneratorId;

  public PostService(PostRepository postRepository, IdGenerator postIdGenerator, Clock clock) {

    this.postIdGeneratorId = postIdGenerator;
    this.postRepository = postRepository;
    this.clock = clock;
  }

  public Post createPost(String userId, String text) throws InapropiateLanguageException {
    Post post = new Post(postIdGeneratorId.next(),
        userId, text, clock.now());
    postRepository.add(post);
    return post;
  }
}
