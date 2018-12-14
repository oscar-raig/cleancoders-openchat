package org.openchat.domain.posts;

import org.openchat.domain.users.IdGenerator;

public class PostService {

  private final PostRepository postRepository;
  private final Clock clock;
  private final LanguageService languageService;
  private IdGenerator postIdGeneratorId;

  public PostService(PostRepository postRepository, IdGenerator postIdGenerator, Clock clock,
      LanguageService languageService) {

    this.postIdGeneratorId = postIdGenerator;
    this.postRepository = postRepository;
    this.clock = clock;
    this.languageService = languageService;
  }

  public Post createPost(String userId, String text) throws InapropiateLanguageException {
    validate(text);
    Post post = new Post(postIdGeneratorId.next(),
        userId, text, clock.now());
    postRepository.add(post);
    return post;
  }

  private void validate(String text) throws InapropiateLanguageException {
    if (languageService.isInapropiate(text)) {
      throw new InapropiateLanguageException();
    }
  }
}
