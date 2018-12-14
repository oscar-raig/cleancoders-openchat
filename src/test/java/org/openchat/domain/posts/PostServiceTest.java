package org.openchat.domain.posts;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.users.IdGenerator;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

  private static final String A_POST_ID = "1";
  private static final String AN_USER_ID = "anUser";
  private static final String A_POST = "aPost";
  private static final LocalDateTime DATE = LocalDateTime.now();
  private static final Post POST = new Post(A_POST_ID, AN_USER_ID, A_POST, DATE);
  private static final String INAPROPIATE_TEXT = "elephant";

  @Mock private PostRepository postRepository;
  @Mock private IdGenerator idGenerator;
  @Mock  private Clock clock;
  @Mock private LanguageService languageService;

  private PostService postService;

  @Before
  public void setUp() {
    postService = new PostService(postRepository, idGenerator, clock, languageService);
  }

  @Test
  public void
  should_create_post() throws InapropiateLanguageException {

    given(idGenerator.next()).willReturn(A_POST_ID);
    given(clock.now()).willReturn(DATE);

    Post post = postService.createPost(AN_USER_ID, A_POST);

    verify(postRepository, times(1)).add(POST);

    assertThat(post).isEqualTo(POST);
  }

  @Test(expected = InapropiateLanguageException.class ) public void
  throw_exception_when_text_contains_inapropiate_words() throws InapropiateLanguageException {

    given(languageService.isInapropiate(INAPROPIATE_TEXT)).willReturn(true);

    postService.createPost(AN_USER_ID, INAPROPIATE_TEXT);
  }
}