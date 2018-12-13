package org.openchat.domain.posts;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.users.IdGenerator;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

  private static final String A_POST_ID = "1";
  @Mock private PostRepository postRepository;
  @Mock private IdGenerator idGenerator;
  @Mock  private Clock clock;
  private PostService postService;
  private String AN_USER_ID = "anUser";
  private String A_POST = "aPost";
  private static final LocalDateTime DATE = LocalDateTime.now();
  private Post POST = new Post(A_POST_ID, AN_USER_ID, A_POST, DATE);

  @Before
  public void setUp() {
    postService = new PostService(postRepository, idGenerator, clock);
  }

  @Test
  public void
  should_create_post() throws InapropiateLanguageException {

    given(idGenerator.next()).willReturn(A_POST_ID);
    given(clock.now()).willReturn(DATE);

    postService.createPost(AN_USER_ID, A_POST);

    verify(postRepository, times(1)).add(POST);
  }
}